package com.example.currencyconverter.ui.currency.usecase

import com.example.currencyconverter.core.MyResult
import com.example.currencyconverter.core.mapper.toUiModel
import com.example.currencyconverter.domain.currencyNameProvider.CurrencyNameProvider
import com.example.currencyconverter.domain.repository.CurrencyRepository
import com.example.currencyconverter.ui.currency.data.CurrencyUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRatesUseCase @Inject constructor(
    private val repository: CurrencyRepository,
    private val currencyNameProvider: CurrencyNameProvider

) {
    fun getRates(
        baseCurrencyCode: String,
        amount: Double,
        isInputMode: Boolean
    ): Flow<MyResult<List<CurrencyUiModel>>> =
        flow<MyResult<List<CurrencyUiModel>>> {
            val rates = repository.getRates(baseCurrencyCode, amount)
            val accounts = repository.getAllAccounts()
            val mapped = rates.mapNotNull { rate ->
                val account = accounts.find { it.code == rate.currency }
                val isSelected = rate.currency == baseCurrencyCode

                if (isInputMode && !isSelected) {
                    val requiredAmount = amount / rate.value
                    if ((account?.amount ?: 0.0) < requiredAmount) return@mapNotNull null
                }

                rate.toUiModel(
                    currencyNameProvider = currencyNameProvider,
                    balance = account?.amount
                )
            }

            emit(MyResult.Success(mapped))
        }.catch { e ->
            emit(MyResult.Failure(Exception(e)))
        }
}