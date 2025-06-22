package com.example.currencyconverter.data.repository

import com.example.currencyconverter.data.dataSource.remote.RatesService
import com.example.currencyconverter.data.dataSource.remote.dto.RateDto
import com.example.currencyconverter.data.dataSource.room.account.dao.AccountDao
import com.example.currencyconverter.data.dataSource.room.account.dbo.AccountDbo
import com.example.currencyconverter.data.dataSource.room.transaction.dao.TransactionDao
import com.example.currencyconverter.data.dataSource.room.transaction.dbo.TransactionDbo
import com.example.currencyconverter.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val ratesService: RatesService,
    private val accountDao: AccountDao,
    private val transactionDao: TransactionDao
) : CurrencyRepository {

    override suspend fun getRates(baseCurrencyCode: String, amount: Double): List<RateDto> {
        return ratesService.getRates(baseCurrencyCode, amount)
    }

    override suspend fun getAllAccounts(): List<AccountDbo> {
        return accountDao.getAll()
    }

    override fun getAccountsFlow(): Flow<List<AccountDbo>> {
        return accountDao.getAllAsFlow()
    }

    override suspend fun insertAccounts(vararg accounts: AccountDbo) {
        accountDao.insertAll(*accounts)
    }
    override suspend fun insertTransactions(vararg transactions: TransactionDbo) {
        transactionDao.insertAll(*transactions)
    }

    override suspend fun getAllTransactions(): List<TransactionDbo> {
        return transactionDao.getAll()
    }
}