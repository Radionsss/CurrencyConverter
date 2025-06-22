
package com.example.currencyconverter.data.dataSource.room.transaction.dao

import com.example.currencyconverter.data.dataSource.room.transaction.dbo.TransactionDbo

class FakeTransactionDao : TransactionDao {

    val transactions = mutableListOf<TransactionDbo>()

    override suspend fun insertAll(vararg transactionsToInsert: TransactionDbo) {
        transactions += transactionsToInsert
    }

    override suspend fun getAll(): List<TransactionDbo> {
        return transactions
    }
}