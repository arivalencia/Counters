package com.ari.counters.data.contracts

import com.ari.counters.data.model.CounterData

interface CounterLocalDataSource {

    suspend fun getAllCounters(): List<CounterData>

    suspend fun insertCounterList(counterList: List<CounterData>)

    suspend fun clearTable()
}