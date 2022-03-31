package com.ari.counters.framework.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ari.counters.data.model.CounterData

@Entity(
    tableName = "counter_table"
)
data class CounterEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "count")
    val count: Int
)

fun CounterEntity.toData(): CounterData = CounterData(id = id, title = title, count = count)

fun CounterData.toEntity(): CounterEntity = CounterEntity(id = id, title = title, count = count)