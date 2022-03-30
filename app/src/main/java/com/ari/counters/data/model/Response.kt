package com.ari.counters.data.model

sealed class Response<T> {
    class Success<T>(val result: T): Response<T>()
    class Error<T>(val error: String): Response<T>()
}