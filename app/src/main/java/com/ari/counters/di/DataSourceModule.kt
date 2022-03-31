package com.ari.counters.di

import com.ari.counters.data.contracts.CounterDataSource
import com.ari.counters.framework.CounterApi
import com.ari.counters.framework.CounterDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Inject
    @Provides
    fun provideCounterDataSource(counterApi: CounterApi): CounterDataSource =
        CounterDataSourceImpl(counterApi)

}