package com.example.currencyconverter.ui.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.dataSource.room.transaction.dbo.TransactionDbo
import com.example.currencyconverter.domain.repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val repository: CurrencyRepository
) : ViewModel() {
    private val _transactions = MutableStateFlow<List<TransactionDbo>>(emptyList())
    val transactions: StateFlow<List<TransactionDbo>> = _transactions

    init {
        viewModelScope.launch {
            _transactions.value = repository.getAllTransactions()
        }
    }
}