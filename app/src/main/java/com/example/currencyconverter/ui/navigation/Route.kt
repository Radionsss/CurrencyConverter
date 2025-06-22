package com.example.currencyconverter.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    data object CurrencyScreen : Route()

    @Serializable
    data object TransactionsScreen : Route()

    @Serializable
    data class ExchangeScreen(
        val fromCode: String,
        val toCode: String,
        val amountToBuy: Double,
        val rate: Double
    )  : Route()
}
