package com.extack.jetmovies.api.commons

import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

inline fun <T> apiCaller(serviceFunction: () -> T): Resource<T> {
    return try {
        Resource.Success(serviceFunction.invoke())
    } catch (e: Exception) {
        when (e) {
            is HttpException -> {
                val responseBody = e.response()?.errorBody()
                Resource.Failure(extractErrorMessage(e.code(), responseBody))
            }
            is SocketTimeoutException -> {
                Resource.Failure(Error.SocketTimeoutError(e.message ?: "Socket Timed out"))
            }
            is IOException -> {
                Resource.Failure(Error.NetworkError(e.message ?: "Network error occured"))
            }
            else -> Resource.Failure(Error.UnknownError(e.message ?: "Something went wrong"))
        }
    }
}