package com.ari.counters.di

import com.ari.counters.data.contracts.CounterDataSource
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
    fun provideCounterRepository(counterDataSource: CounterDataSource): CounterRepository =
        CounterRepository(counterDataSource)

}