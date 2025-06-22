package com.example.currencyconverter.data.dataSource.room.account.dao

import com.example.currencyconverter.data.dataSource.room.account.dbo.AccountDbo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeAccountDao : AccountDao {

    private val accounts = mutableMapOf<String, AccountDbo>()
    private val accountsFlow = MutableStateFlow<List<AccountDbo>>(emptyList())

    override suspend fun insertAll(vararg accountsToInsert: AccountDbo) {
        accountsToInsert.forEach {
            accounts[it.code] = it
        }
        accountsFlow.value = accounts.values.toList()
    }

    override suspend fun getAll(): List<AccountDbo> {
        return accounts.values.toList()
    }

    override fun getAllAsFlow(): Flow<List<AccountDbo>> {
        return accountsFlow
    }

    fun get(code: String): AccountDbo? = accounts[code]
}