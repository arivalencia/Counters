package com.ari.counters.di

import com.ari.counters.data.contracts.CounterLocalDataSource
import com.ari.counters.data.contracts.CounterRemoteDataSource
import com.ari.counters.framework.CounterApi
import com.ari.counters.framework.CounterLocalDataSourceImpl
import com.ari.counters.framework.CounterRemoteDataSourceImpl
import com.ari.counters.framework.database.dao.CounterDao
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
    fun provideCounterRemoteDataSource(counterApi: CounterApi): CounterRemoteDataSource =
        CounterRemoteDataSourceImpl(counterApi)

    @Inject
    @Provides
    fun provideCounterLocalDataSource(counterDao: CounterDao): CounterLocalDataSource =
        CounterLocalDataSourceImpl(counterDao)

}