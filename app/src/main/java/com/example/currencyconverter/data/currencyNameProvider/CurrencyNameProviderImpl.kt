package com.example.currencyconverter.data.currencyNameProvider


import android.content.Context
import com.example.currencyconverter.R
import com.example.currencyconverter.data.dataSource.remote.dto.RateDto
import com.example.currencyconverter.domain.currencyNameProvider.CurrencyNameProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.random.Random

class CurrencyNameProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : CurrencyNameProvider {

    override fun getCurrencyName(code: String): String {
        return when (code) {
            "USD" -> context.getString(R.string.currency_usd)
            "EUR" -> context.getString(R.string.currency_eur)
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
            "AUD" -> context.getString(R.string.currency_aud)
            "ZAR" -> context.getString(R.string.currency_zar)
            else -> code
        }
    }
}