package com.ari.counters.data.model

import com.google.gson.annotations.SerializedName

data class AddCounterBody(
    @SerializedName("title")
    val title: String
)