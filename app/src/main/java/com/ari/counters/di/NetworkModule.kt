package com.ari.counters.di

import com.ari.counters.framework.CounterApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Inject
    @Provides
    fun provideCounterApi(retrofit: Retrofit): CounterApi = retrofit.create(CounterApi::class.java)

}