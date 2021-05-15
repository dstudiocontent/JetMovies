package com.extack.jetmovies.api.commons

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure(val error: Error) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}