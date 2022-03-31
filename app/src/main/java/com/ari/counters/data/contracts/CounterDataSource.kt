package com.ari.counters.data.contracts

import com.ari.counters.data.model.CounterData
import com.ari.counters.data.model.Response

interface CounterDataSource {
    suspend fun getAllCounter(): Response<List<CounterData>>

    suspend fun addCounter(title: String): Response<CounterData>

    suspend fun deleteCounter(counterId: String): Response<Any>

    suspend fun incrementCounter(counterId: String): Response<CounterData>

    suspend fun decrementCounter(counterId: String): Response<CounterData>
}
