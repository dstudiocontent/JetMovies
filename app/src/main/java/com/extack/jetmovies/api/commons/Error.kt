package com.extack.jetmovies.api.commons

import okhttp3.ResponseBody
import org.json.JSONObject

sealed class Error(val errorMessage: String) {

    data class HttpError(val code: Int, val message: String) : Error(message)

    data class NetworkError(val message: String) : Error(message)

    data class SocketTimeoutError(val message: String) : Error(message)

    data class UnknownError(val message: String) : Error(message)

}


fun extractErrorMessage(httpCode: Int, responseBody: ResponseBody?): Error {
    return try {
        val jsonObject = JSONObject(responseBody?.string()?:"")
        when {
            jsonObject.has(MESSAGE_KEY) -> Error.HttpError(httpCode,jsonObject.getString(MESSAGE_KEY))
            jsonObject.has(ERROR_KEY) -> Error.HttpError(httpCode, jsonObject.getString(ERROR_KEY))
            else -> Error.UnknownError("Something wrong happened")
        }
    } catch (e: Exception) {
        Error.UnknownError(e.message?:"Something went wrong")
    }
}