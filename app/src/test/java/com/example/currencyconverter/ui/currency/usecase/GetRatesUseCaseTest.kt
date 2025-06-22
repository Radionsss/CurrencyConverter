package com.example.currencyconverter.ui.currency.usecase

import com.example.currencyconverter.core.MyResult
import com.example.currencyconverter.data.dataSource.remote.dto.RateDto
import com.example.currencyconverter.domain.currencyNameProvider.CurrencyNameProvider
import com.example.currencyconverter.domain.repository.CurrencyRepository
import com.example.currencyconverter.ui.currency.data.CurrencyUiModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.junit.Assert.assertEquals

class GetRatesUseCaseTest {

    private lateinit var repository: CurrencyRepository
    private lateinit var currencyNameProvider: CurrencyNameProvider
    private lateinit var useCase: GetRatesUseCase

    @Before
    fun setUp() {
        repository = mock()
        currencyNameProvider = mock()
        useCase = GetRatesUseCase(repository, currencyNameProvider)
    }

    @Test
    fun `getRates returns filtered and mapped currencies`() = runTest {
        val rates = listOf(
            RateDto("USD", 1.0),
            RateDto("EUR", 2.0),
            RateDto("RUB", 0.5)
        )
        val accounts = listOf(
            com.example.currencyconverter.data.dataSource.room.account.dbo.AccountDbo("USD", 10.0),
            com.example.currencyconverter.data.dataSource.room.account.dbo.AccountDbo("EUR", 1.0)
        )

        whenever(repository.getRates("USD", 1.0)).thenReturn(rates)
        whenever(repository.getAllAccounts()).thenReturn(accounts)
        whenever(currencyNameProvider.getCurrencyName("USD")).thenReturn("Доллар США")
        whenever(currencyNameProvider.getCurrencyName("EUR")).thenReturn("Евро")
        whenever(currencyNameProvider.getCurrencyName("RUB")).thenReturn("Рубль")

        val result = useCase.getRates("USD", 1.0, isInputMode = true).first()

        require(result is MyResult.Success)

        val models: List<CurrencyUiModel> = result.data

        assertEquals(2, models.size)
        assertEquals("USD", models[0].code)
        assertEquals("EUR", models[1].code)
    }
}
