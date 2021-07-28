package com.extack.jetmovies.api.commons

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure(val error: Error) : Resource<Nothing>()
}

fun <T> Resource<T>.onSuccess(block: (data: T) -> Unit): Resource<T> {
    if (this is Resource.Success<T>) {
        block(data)
    }
    return this
}

fun <T> Resource<T>.onFailure(block: (error: Error) -> Unit): Resource<T> {
    if (this is Resource.Failure) {
        block(error)
    }
    return this
}