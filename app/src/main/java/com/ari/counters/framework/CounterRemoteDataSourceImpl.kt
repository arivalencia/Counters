package com.ari.counters.framework

import com.ari.counters.core.Constants
import com.ari.counters.data.contracts.CounterRemoteDataSource
import com.ari.counters.data.model.*
import javax.inject.Inject

class CounterRemoteDataSourceImpl @Inject constructor(
    private val counterApi: CounterApi
): CounterRemoteDataSource {

    override suspend fun getAllCounter(): Response<List<CounterData>> = try {
        val response = counterApi.getAllCounters()

        if (response.isSuccessful) {
            response.body()?.let { counters ->
                Response.Success(counters)
            } ?: Response.Error(Constants.BODY_NULL_ERROR)
        } else {
            Response.Error(response.message())
        }

    } catch (e: Exception) {
        Response.Error(e.message ?: Constants.GENERIC_ERROR)
    }

    override suspend fun addCounter(title: String): Response<List<CounterData>> = try {
        val response = counterApi.addCounter(AddCounterBody(title))

        if (response.isSuccessful) {
            response.body()?.let { counters ->
                Response.Success(counters)
            } ?: Response.Error(Constants.BODY_NULL_ERROR)
        } else {
            Response.Error(response.message())
        }

    } catch (e: Exception) {
        Response.Error(e.message ?: Constants.GENERIC_ERROR)
    }

    override suspend fun deleteCounter(counterId: String): Response<List<CounterData>> = try {
        val response = counterApi.deleteCounter(DeleteCounterBody(counterId))

        if (response.isSuccessful) {
            response.body()?.let { counters ->
                Response.Success(counters)
            } ?: Response.Error(Constants.BODY_NULL_ERROR)
        } else {
            Response.Error(response.message())
        }

    } catch (e: Exception) {
        Response.Error(e.message ?: Constants.GENERIC_ERROR)
    }

    override suspend fun incrementCounter(counterId: String): Response<List<CounterData>> = try {
        val response = counterApi.incrementCounter(IncDecCounterBody(counterId))

        if (response.isSuccessful) {
            response.body()?.let { counters ->
                Response.Success(counters)
            } ?: Response.Error(Constants.BODY_NULL_ERROR)
        } else {
            Response.Error(response.message())
        }

    } catch (e: Exception) {
        Response.Error(e.message ?: Constants.GENERIC_ERROR)
    }

    override suspend fun decrementCounter(counterId: String): Response<List<CounterData>> = try {
        val response = counterApi.decrementCounter(IncDecCounterBody(counterId))

        if (response.isSuccessful) {
            response.body()?.let { counters ->
                Response.Success(counters)
            } ?: Response.Error(Constants.BODY_NULL_ERROR)
        } else {
            Response.Error(response.message())
        }

    } catch (e: Exception) {
        Response.Error(e.message ?: Constants.GENERIC_ERROR)
    }
}