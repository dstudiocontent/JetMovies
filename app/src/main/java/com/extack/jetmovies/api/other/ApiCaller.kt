package com.extack.jetmovies.api.other

inline fun <T> apiCaller(serviceFunction: () -> T): Resource<T> {
    return try {
        Resource.Success(serviceFunction.invoke())
    } catch (e: Exception) {
        Resource.Failure(e)
    }
}