package com.example.currencyconverter.ui.exchangeScreen.usecase

import com.example.currencyconverter.data.dataSource.room.account.dao.AccountDao
import com.example.currencyconverter.data.dataSource.room.account.dbo.AccountDbo
import com.example.currencyconverter.data.dataSource.room.transaction.dao.TransactionDao
import com.example.currencyconverter.data.dataSource.room.transaction.dbo.TransactionDbo
import java.time.LocalDateTime
import javax.inject.Inject

class BuyCurrencyUseCase @Inject constructor(
    private val accountDao: AccountDao,
    private val transactionDao: TransactionDao
) {
    suspend fun execute(from: String, to: String, amountFrom: Double, amountTo: Double) {
        val accounts = accountDao.getAll().associateBy { it.code }.toMutableMap()

        val fromAccount = accounts[from] ?: AccountDbo(from, 0.0)
        val toAccount = accounts[to] ?: AccountDbo(to, 0.0)

        // Обновление балансов
        accounts[from] = fromAccount.copy(amount = fromAccount.amount - amountFrom)
        accounts[to] = toAccount.copy(amount = toAccount.amount + amountTo)

        // Сохраняем обновления
        accountDao.insertAll(accounts[from]!!, accounts[to]!!)

        // Сохраняем транзакцию
        transactionDao.insertAll(
            TransactionDbo(
                id = 0,
                from = from,
                to = to,
                fromAmount = amountFrom,
                toAmount = amountTo,
                dateTime = LocalDateTime.now()
            )
        )
    }
}