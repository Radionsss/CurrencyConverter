package com.example.currencyconverter.ui.currency


import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.currencyconverter.core.MyResult

@Composable
fun CurrencyListScreen(
    onNavigateToExchange: (fromCode: String, toCode: String, amountToBuy: Double, rate: Double) -> Unit
) {
    val viewModel: CurrencyListViewModel = hiltViewModel()
    val resultState = viewModel.ratesResult.collectAsState()
    val amount = viewModel.amountInput.collectAsState()
    val selectedCurrency = viewModel.selectedCurrency.collectAsState()
    val screenMode = viewModel.screenMode.collectAsState()
    var amountText by remember { mutableStateOf("1.0") }

    LaunchedEffect(amountText) {
        amountText.toDoubleOrNull()?.let { newAmount ->
            viewModel.onAmountChanged(newAmount)
        }
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text = selectedCurrency.value)
            Spacer(modifier = Modifier.width(8.dp))

            Spacer(modifier = Modifier.width(8.dp))
        }

        when (val result = resultState.value) {
            is MyResult.Success -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(result.data) { currency ->
                        CurrencyListItem(
                            currency = currency,
                            isSelected = currency.code == selectedCurrency.value,
                            isEditable = currency.code == selectedCurrency.value && screenMode.value == CurrencyScreenMode.INPUT,
                            amountText = amountText,
                            onClick = {
                                if (currency.code == selectedCurrency.value) {
                                    viewModel.activateInputMode()
                                } else {
                                    if (screenMode.value == CurrencyScreenMode.INPUT) {
                                        val amountToBuy = amount.value

                                        val parsed = currency.formattedRate.replace(",", ".").toDoubleOrNull() ?: 0.0
                                        Log.d(
                                            "amountToBuy",
                                            "parsed=${parsed}"
                                        )
                                        onNavigateToExchange(
                                            currency.code,
                                            selectedCurrency.value,
                                            amountToBuy,
                                            parsed
                                        )
                                    } else {
                                        viewModel.onCurrencySelected(currency.code)
                                    }
                                }
                            },
                            onAmountChange = {
                                amountText = it
                            },
                            onAmountReset = {
                                viewModel.setListMode()
                                viewModel.onAmountChanged(1.0)
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }

            is MyResult.Loading -> Box(Modifier.fillMaxSize(), Alignment.Center) {
                Text("Загрузка...")
            }

            is MyResult.Failure -> Box(Modifier.fillMaxSize(), Alignment.Center) {
                Text("Ошибка: ${result.exception.message}")
            }

            else -> {}
        }
    }
}

enum class CurrencyScreenMode {
    LIST,
    INPUT
}