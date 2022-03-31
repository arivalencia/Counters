package com.ari.counters.framework.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ari.counters.framework.database.entities.CounterEntity

@Dao
interface CounterDao {

    @Query("SELECT * FROM counter_table")
    suspend fun getAllCountersFromDB(): List<CounterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(counters: List<CounterEntity>)

    @Query("DELETE FROM counter_table")
    suspend fun clearTable()

}