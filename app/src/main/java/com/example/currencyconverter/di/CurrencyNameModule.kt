package com.example.currencyconverter.di

import com.example.currencyconverter.data.currencyNameProvider.CurrencyNameProviderImpl
import com.example.currencyconverter.domain.currencyNameProvider.CurrencyNameProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CurrencyNameModule {

    @Binds
    abstract fun bindCurrencyNameProvider(
        impl: CurrencyNameProviderImpl
    ): CurrencyNameProvider
}