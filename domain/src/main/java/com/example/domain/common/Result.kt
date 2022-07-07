package com.example.domain.common

import java.lang.Exception

sealed class Result <T> {

    data class Success<T>(val data: T) : Result<T>()

    data class Error<T>(val error: Exception) : Result<T>()
}

inline fun <T : Any> Result<T>.onSuccessValue(block: (T) -> Unit): Result<T> {
    if (this is Result.Success) {
        block.invoke(data)
    }
    return this
}

inline fun <T: Any> Result<T>.onErrorValue(block: (Throwable) -> Unit): Result<T> {
    if (this is Result.Error) {
        block.invoke(error)
    }
    return this
}

suspend fun <Data> asResult(block: suspend () -> Data): Result<Data> {
    return try {
        val response = block()
        Result.Success(response)
    } catch (exc: Exception) {
        Result.Error(exc)
    }
}
