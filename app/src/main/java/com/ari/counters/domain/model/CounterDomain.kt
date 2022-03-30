package com.ari.counters.domain.model

import com.ari.counters.data.model.CounterData

data class CounterDomain(
    val id: String,
    val title: String,
    var count: Int
)

fun CounterData.toDomain(): CounterDomain = CounterDomain(id = id, title = title, count = count)