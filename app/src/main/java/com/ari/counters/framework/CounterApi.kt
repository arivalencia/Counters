package com.ari.counters.framework

import com.ari.counters.data.model.AddCounterBody
import com.ari.counters.data.model.CounterData
import com.ari.counters.data.model.DeleteCounterBody
import com.ari.counters.data.model.IncDecCounterBody
import retrofit2.Response
import retrofit2.http.*

interface CounterApi {

    @GET("counters")
    suspend fun getAllCounters(): Response<List<CounterData>>

    @POST("counter")
    suspend fun addCounter(@Body body: AddCounterBody): Response<List<CounterData>>

    @HTTP(method = "DELETE", path = "counter", hasBody = true)
    suspend fun deleteCounter(@Body body: DeleteCounterBody): Response<List<CounterData>>

    @POST("counter/inc")
    suspend fun incrementCounter(@Body body: IncDecCounterBody): Response<List<CounterData>>

    @POST("counter/dec")
    suspend fun decrementCounter(@Body body: IncDecCounterBody): Response<List<CounterData>>

}