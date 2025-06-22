package com.example.currencyconverter.ui.exchangeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.dataSource.room.account.dbo.AccountDbo
import com.example.currencyconverter.domain.repository.CurrencyRepository
import com.example.currencyconverter.ui.exchangeScreen.usecase.BuyCurrencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModel @Inject constructor(
    private val repository: CurrencyRepository,
    private val buyCurrencyUseCase: BuyCurrencyUseCase,

    ) : ViewModel() {

    private val _accounts = MutableStateFlow<List<AccountDbo>>(emptyList())
    val accounts: StateFlow<List<AccountDbo>> = _accounts

    private val _rate = MutableStateFlow<Double?>(null)
    val rate: StateFlow<Double?> = _rate

    fun loadRate(from: String, to: String) {
        viewModelScope.launch {
           // val rates = getExchangeRateUseCase.getRate(from, to)

            val rates = repository.getRates(from, 1.0)
            val matching = rates.find { it.currency == to }
            _rate.value = matching?.value
        }
    }

    fun loadAccounts() {
        viewModelScope.launch {
            _accounts.value = repository.getAllAccounts()
        }
    }

    fun buy(from: String, to: String, amountFrom: Double, amountTo: Double, onSuccess: () -> Unit) {
        viewModelScope.launch {
            buyCurrencyUseCase.execute(from, to, amountFrom, amountTo)
            onSuccess()
        }
    }

    init {
        viewModelScope.launch {
            repository.getAccountsFlow().collect { _accounts.value = it }
        }
    }
}