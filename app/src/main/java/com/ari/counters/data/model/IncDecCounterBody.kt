package com.ari.counters.data.model

import com.google.gson.annotations.SerializedName

// Body for increment and decrement counter
data class IncDecCounterBody(
    @SerializedName("id")
    val counterId: String
)
