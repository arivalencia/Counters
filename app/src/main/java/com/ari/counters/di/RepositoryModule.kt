package com.ari.counters.di

import com.ari.counters.data.contracts.CounterLocalDataSource
import com.ari.counters.data.contracts.CounterRemoteDataSource
import com.ari.counters.data.repository.CounterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Inject
    @Provides
    fun provideCounterRepository(
        counterRemoteDataSource: CounterRemoteDataSource,
        counterLocalDataSource: CounterLocalDataSource
    ): CounterRepository = CounterRepository(counterRemoteDataSource, counterLocalDataSource)

}