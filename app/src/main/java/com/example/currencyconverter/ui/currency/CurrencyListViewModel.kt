package com.example.currencyconverter.ui.currency


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.core.MyResult
import com.example.currencyconverter.data.dataSource.room.account.dbo.AccountDbo
import com.example.currencyconverter.domain.repository.CurrencyRepository
import com.example.currencyconverter.ui.currency.data.CurrencyUiModel
import com.example.currencyconverter.ui.currency.usecase.GetRatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    private val getRatesUseCase: GetRatesUseCase,
    private val repository: CurrencyRepository
) : ViewModel() {
    private val _amountInput = MutableStateFlow(1.0)
    val amountInput: StateFlow<Double> = _amountInput
    private val _parsedAmount = MutableStateFlow(1.0)
    private val _selectedCurrency = MutableStateFlow("RUB")
    val selectedCurrency: StateFlow<String> = _selectedCurrency

    private val _ratesResult = MutableStateFlow<MyResult<List<CurrencyUiModel>>>(MyResult.Default)
    val ratesResult: StateFlow<MyResult<List<CurrencyUiModel>>> = _ratesResult

    private val _screenMode = MutableStateFlow(CurrencyScreenMode.LIST)
    val screenMode: StateFlow<CurrencyScreenMode> = _screenMode
    fun setListMode() {
        _screenMode.value = CurrencyScreenMode.LIST
    }
    init {
        viewModelScope.launch {
            val accounts = repository.getAllAccounts()
            if (accounts.isEmpty()) {
                val rubAccount = AccountDbo(
                    code = "RUB",
                    amount = 75000.0
                )
                repository.insertAccounts(rubAccount)
            }
        }
        restartRateUpdates()
    }
    fun activateInputMode() {
        _screenMode.value = CurrencyScreenMode.INPUT
    }
    private var lastParsedAmount: Double = 1.0

    fun onAmountChanged(value: Double) {
        _amountInput.value = value

        val parsed = value
        if ( parsed != lastParsedAmount) {
            lastParsedAmount = parsed
            _parsedAmount.value = parsed
            _screenMode.value = if (parsed == 1.0) CurrencyScreenMode.LIST else CurrencyScreenMode.INPUT
            restartRateUpdates()
        }
    }
    fun onCurrencySelected(code: String) {
        _selectedCurrency.value = code
        restartRateUpdates()
    }
    private var timerJob: Job? = null

    private fun restartRateUpdates() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                val base = selectedCurrency.value
                val amount = amountInput.value
                val isInput = screenMode.value == CurrencyScreenMode.INPUT

                getRatesUseCase
                    .getRates(
                        baseCurrencyCode = base,
                        amount = amount,
                        isInputMode = isInput
                    ).collect {
                    _ratesResult.value = it
                }
                delay(1000L)
            }
        }
    }
}