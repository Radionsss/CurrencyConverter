package com.example.currencyconverter.domain.repository

import com.example.currencyconverter.data.dataSource.remote.dto.RateDto
import com.example.currencyconverter.data.dataSource.room.account.dbo.AccountDbo
import com.example.currencyconverter.data.dataSource.room.transaction.dbo.TransactionDbo
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    suspend fun getRates(baseCurrencyCode: String, amount: Double): List<RateDto>
    suspend fun getAllAccounts(): List<AccountDbo>
    fun getAccountsFlow(): Flow<List<AccountDbo>>
    suspend fun insertAccounts(vararg accounts: AccountDbo)
    suspend fun insertTransactions(vararg transactions: TransactionDbo)
    suspend fun getAllTransactions(): List<TransactionDbo>

}