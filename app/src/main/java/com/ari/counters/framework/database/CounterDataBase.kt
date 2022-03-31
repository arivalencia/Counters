package com.ari.counters.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ari.counters.framework.database.dao.CounterDao
import com.ari.counters.framework.database.entities.CounterEntity

@Database(
    entities = [CounterEntity::class],
    version = 1
)
abstract class CounterDataBase: RoomDatabase() {
    abstract fun getDao(): CounterDao
}