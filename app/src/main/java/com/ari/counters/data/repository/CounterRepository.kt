package com.ari.counters.data.repository

import com.ari.counters.data.contracts.CounterLocalDataSource
import com.ari.counters.data.contracts.CounterRemoteDataSource
import com.ari.counters.data.model.CounterData
import com.ari.counters.data.model.Response
import javax.inject.Inject

class CounterRepository @Inject constructor(
    private val counterRemoteDataSource: CounterRemoteDataSource,
    private val counterLocalDataSource: CounterLocalDataSource
) {

    suspend fun getAllCounter(): Response<List<CounterData>> =
        counterRemoteDataSource.getAllCounter()

    suspend fun addCounter(title: String): Response<List<CounterData>> =
        counterRemoteDataSource.addCounter(title)

    suspend fun deleteCounter(counterId: String): Response<List<CounterData>> =
        counterRemoteDataSource.deleteCounter(counterId)

    suspend fun incrementCounter(counterId: String): Response<List<CounterData>> =
        counterRemoteDataSource.incrementCounter(counterId)

    suspend fun decrementCounter(counterId: String): Response<List<CounterData>> =
        counterRemoteDataSource.decrementCounter(counterId)

    suspend fun getAllLocalCounters(): List<CounterData> = counterLocalDataSource.getAllCounters()

    suspend fun insertCounterListInLocal(counterList: List<CounterData>) =
        counterLocalDataSource.insertCounterList(counterList)

    suspend fun clearLocalTable() = counterLocalDataSource.clearTable()

}