package com.ari.counters.data.repository

import com.ari.counters.data.contracts.CounterDataSource
import com.ari.counters.data.model.CounterData
import com.ari.counters.data.model.Response
import javax.inject.Inject

class CounterRepository @Inject constructor(
    private val counterDataSource: CounterDataSource
) {

    suspend fun getAllCounter(): Response<List<CounterData>> = counterDataSource.getAllCounter()

    suspend fun addCounter(title: String): Response<CounterData> =
        counterDataSource.addCounter(title)

    suspend fun deleteCounter(counterId: String): Response<Any?> =
        counterDataSource.deleteCounter(counterId)

    suspend fun incrementCounter(counterId: String): Response<CounterData> =
        counterDataSource.incrementCounter(counterId)

    suspend fun decrementCounter(counterId: String): Response<CounterData> =
        counterDataSource.decrementCounter(counterId)

}