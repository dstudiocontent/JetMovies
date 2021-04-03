package com.extack.jetmovies.api.other

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure(val error: Throwable) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}