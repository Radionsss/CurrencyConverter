package com.example.currencyconverter.ui.common.extensions

import com.example.currencyconverter.R

import android.content.Context

fun String.getCurrencyFullName(context: Context): String {
    return when (this) {
        "USD" -> context.getString(R.string.currency_usd)
        "EUR" -> context.getString(R.string.currency_eur)
        "AUD" -> context.getString(R.string.currency_aud)
        "BGN" -> context.getString(R.string.currency_bgn)
        "BRL" -> context.getString(R.string.currency_brl)
        "CAD" -> context.getString(R.string.currency_cad)
        "CHF" -> context.getString(R.string.currency_chf)
        "CNY" -> context.getString(R.string.currency_cny)
        "CZK" -> context.getString(R.string.currency_czk)
        "DKK" -> context.getString(R.string.currency_dkk)
        "GBP" -> context.getString(R.string.currency_gbp)
        "HKD" -> context.getString(R.string.currency_hkd)
        "HRK" -> context.getString(R.string.currency_hrk)
        "HUF" -> context.getString(R.string.currency_huf)
        "IDR" -> context.getString(R.string.currency_idr)
        "ILS" -> context.getString(R.string.currency_ils)
        "INR" -> context.getString(R.string.currency_inr)
        "ISK" -> context.getString(R.string.currency_isk)
        "JPY" -> context.getString(R.string.currency_jpy)
        "KRW" -> context.getString(R.string.currency_krw)
        "MXN" -> context.getString(R.string.currency_mxn)
        "MYR" -> context.getString(R.string.currency_myr)
        "NOK" -> context.getString(R.string.currency_nok)
        "NZD" -> context.getString(R.string.currency_nzd)
        "PHP" -> context.getString(R.string.currency_php)
        "PLN" -> context.getString(R.string.currency_pln)
        "RON" -> context.getString(R.string.currency_ron)
        "RUB" -> context.getString(R.string.currency_rub)
        "SEK" -> context.getString(R.string.currency_sek)
        "SGD" -> context.getString(R.string.currency_sgd)
        "THB" -> context.getString(R.string.currency_thb)
        "TRY" -> context.getString(R.string.currency_try)
        "ZAR" -> context.getString(R.string.currency_zar)
        else -> this
    }
}

fun String.getFlagResId(): Int {
    return when (this) {
        "USD" -> R.drawable.american
        "EUR" -> R.drawable.euro
        "AUD" -> R.drawable.australia
        "BGN" -> R.drawable.bulgaria
        "BRL" -> R.drawable.braz
        "CAD" -> R.drawable.canada
        "CHF" -> R.drawable.switzerland
        "CNY" -> R.drawable.china
        "CZK" -> R.drawable.czech_republic
        "DKK" -> R.drawable.denmark
        "GBP" -> R.drawable.eng
        "HKD" -> R.drawable.hong_kong
        "HRK" -> R.drawable.croatia
        "HUF" -> R.drawable.hungary
        "IDR" -> R.drawable.indonesia
        "ILS" -> R.drawable.israel
        "INR" -> R.drawable.india
        "ISK" -> R.drawable.iceland
        "JPY" -> R.drawable.japan
        "KRW" -> R.drawable.south_korea
        "MXN" -> R.drawable.mexico
        "MYR" -> R.drawable.malaysia
        "NOK" -> R.drawable.norway
        "NZD" -> R.drawable.new_zealand
        "PHP" -> R.drawable.philippines
        "PLN" -> R.drawable.poland
        "RON" -> R.drawable.romania
        "RUB" -> R.drawable.rus
        "SEK" -> R.drawable.sweden
        "SGD" -> R.drawable.singapore
        "THB" -> R.drawable.thailand
        "TRY" -> R.drawable.turkey
        "ZAR" -> R.drawable.south_africa
        else -> R.drawable.euro
    }
}
fun String.getCurrencySymbol(): String {
    return when (this) {
        "USD" -> "$"
        "EUR" -> "€"
        "AUD" -> "A$"
        "BGN" -> "лв"
        "BRL" -> "R$"
        "CAD" -> "CA$"
        "CHF" -> "CHF"
        "CNY" -> "¥"
        "CZK" -> "Kč"
        "DKK" -> "kr"
        "GBP" -> "£"
        "HKD" -> "HK$"
        "HRK" -> "kn"
        "HUF" -> "Ft"
        "IDR" -> "Rp"
        "ILS" -> "₪"
        "INR" -> "₹"
        "ISK" -> "kr"
        "JPY" -> "¥"
        "KRW" -> "₩"
        "MXN" -> "MX$"
        "MYR" -> "RM"
        "NOK" -> "kr"
        "NZD" -> "NZ$"
        "PHP" -> "₱"
        "PLN" -> "zł"
        "RON" -> "lei"
        "RUB" -> "₽"
        "SEK" -> "kr"
        "SGD" -> "S$"
        "THB" -> "฿"
        "TRY" -> "₺"
        "ZAR" -> "R"
        else -> this
    }
}