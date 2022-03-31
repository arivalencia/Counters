package com.ari.counters.data.model

import com.google.gson.annotations.SerializedName

data class DeleteCounterBody(
    @SerializedName("id")
    val counterId: String
)