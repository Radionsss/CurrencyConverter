package com.example.currencyconverter.core.mapper

import com.example.currencyconverter.data.dataSource.remote.dto.RateDto
import com.example.currencyconverter.domain.currencyNameProvider.CurrencyNameProvider
import com.example.currencyconverter.ui.common.extensions.getFlagResId
import com.example.currencyconverter.ui.currency.data.CurrencyUiModel
import java.util.Locale

fun RateDto.toUiModel(    currencyNameProvider: CurrencyNameProvider,
     balance: Double? = null): CurrencyUiModel {
    return CurrencyUiModel(
        code = currency,
        name = currencyNameProvider.getCurrencyName(currency),
        flagUrl = currency.getFlagResId(),
        formattedRate = String.format(Locale.US, "%.4f", value),
        balance = balance?.let { String.format(Locale.US, "%.2f", it) }
    )
}
