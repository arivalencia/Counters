package com.ari.counters.di

import com.ari.counters.data.repository.CounterRepository
import com.ari.counters.domain.usecases.AddCounterUseCase
import com.ari.counters.domain.usecases.GetAllCountersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    @Inject
    @Provides
    fun provideGetAllCountersUseCase(counterRepository: CounterRepository): GetAllCountersUseCase =
        GetAllCountersUseCase(
            counterRepository
        )

    @Inject
    @Provides
    fun provideAddCounterUseCase(counterRepository: CounterRepository): AddCounterUseCase =
        AddCounterUseCase(
            counterRepository
        )

}