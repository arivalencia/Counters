package com.ari.counters.data.contracts

import com.ari.counters.data.model.Response
import com.ari.counters.domain.model.CounterDomain

interface CounterDataSource {
    suspend fun addCounter(title: String): Response<CounterDomain>

    suspend fun deleteCounter(counterId: String): Response<Any>

    suspend fun incrementCounter(counterId: String): Response<CounterDomain>

    suspend fun decrementCounter(counterId: String): Response<CounterDomain>
}
