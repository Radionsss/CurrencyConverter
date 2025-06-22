package com.example.currencyconverter.ui.exchangeScreen.usecase

import com.example.currencyconverter.data.dataSource.room.account.dao.FakeAccountDao
import com.example.currencyconverter.data.dataSource.room.account.dbo.AccountDbo
import com.example.currencyconverter.data.dataSource.room.transaction.dao.FakeTransactionDao
import com.example.currencyconverter.ui.exchangeScreen.usecase.BuyCurrencyUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
class BuyCurrencyUseCaseTest {

    private lateinit var accountDao: FakeAccountDao
    private lateinit var transactionDao: FakeTransactionDao
    private lateinit var useCase: BuyCurrencyUseCase

    @Before
    fun setUp() {
        accountDao = FakeAccountDao()
        transactionDao = FakeTransactionDao()
        useCase = BuyCurrencyUseCase(accountDao, transactionDao)
    }

    @Test
    fun `execute should update accounts and save transaction`() = runTest {
        accountDao.insertAll(
            AccountDbo("RUB", 10000.0),
            AccountDbo("USD", 100.0)
        )

        useCase.execute("RUB", "USD", amountFrom = 5000.0, amountTo = 50.0)

        val updatedRUB = accountDao.get("RUB")
        val updatedUSD = accountDao.get("USD")
        val transaction = transactionDao.transactions.firstOrNull()

        assertEquals(5000.0, updatedRUB?.amount)
        assertEquals(150.0, updatedUSD?.amount)

        assertEquals("RUB", transaction?.from)
        assertEquals("USD", transaction?.to)
        assertEquals(5000.0, transaction?.fromAmount)
        assertEquals(50.0, transaction?.toAmount)
    }
}