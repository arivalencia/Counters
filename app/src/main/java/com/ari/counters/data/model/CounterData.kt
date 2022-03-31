package com.ari.counters.data.model

import com.google.gson.annotations.SerializedName

data class CounterData(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("count")
    val count: Int
)