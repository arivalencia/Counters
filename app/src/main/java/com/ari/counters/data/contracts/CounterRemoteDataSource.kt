package com.ari.counters.data.contracts

import com.ari.counters.data.model.CounterData
import com.ari.counters.data.model.Response

interface CounterRemoteDataSource {
    suspend fun getAllCounter(): Response<List<CounterData>>

    suspend fun addCounter(title: String): Response<List<CounterData>>

    suspend fun deleteCounter(counterId: String): Response<List<CounterData>>

    suspend fun incrementCounter(counterId: String): Response<List<CounterData>>

    suspend fun decrementCounter(counterId: String): Response<List<CounterData>>
}
