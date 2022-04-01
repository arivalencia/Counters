package com.ari.counters.domain.model

import com.ari.counters.data.model.CounterData

data class CounterDomain(
    val id: String,
    val title: String,
    var count: Int,
    var isSelected: Boolean = false
)

// Get info from counter for share data in text plain
fun CounterDomain.getInfoToShare(): String {
    return "$title: $count\n"
}

// Get info from counter list for share data in text plain
fun getInfoToShareFromCounterList(counters: List<CounterDomain>): String {
    val info = StringBuilder()
    counters.forEach { counter ->
        info.append(counter.getInfoToShare())
    }
    return info.toString()
}

fun CounterData.toDomain(): CounterDomain = CounterDomain(id = id, title = title, count = count)
