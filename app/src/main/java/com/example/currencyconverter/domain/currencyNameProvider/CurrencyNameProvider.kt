package com.example.currencyconverter.domain.currencyNameProvider

interface CurrencyNameProvider {
    fun getCurrencyName(code: String): String
}