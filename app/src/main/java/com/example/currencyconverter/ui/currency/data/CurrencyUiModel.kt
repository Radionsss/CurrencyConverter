package com.example.currencyconverter.ui.currency.data

data class CurrencyUiModel(
    val code: String,
    val name: String,
    val flagUrl: Int,
    val formattedRate: String,
    val balance: String? = null,

)