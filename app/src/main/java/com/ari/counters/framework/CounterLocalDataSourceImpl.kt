package com.ari.counters.framework

import com.ari.counters.data.contracts.CounterLocalDataSource
import com.ari.counters.data.model.CounterData
import com.ari.counters.framework.database.dao.CounterDao
import com.ari.counters.framework.database.entities.toData
import com.ari.counters.framework.database.entities.toEntity
import javax.inject.Inject

class CounterLocalDataSourceImpl @Inject constructor(
    private val counterDao: CounterDao
) : CounterLocalDataSource {

    override suspend fun getAllCounters(): List<CounterData> =
        counterDao.getAllCountersFromDB().map { it.toData() }

    override suspend fun insertCounterList(counterList: List<CounterData>) =
        counterDao.insertList(counterList.map { it.toEntity() })

    override suspend fun clearTable() = counterDao.clearTable()
}