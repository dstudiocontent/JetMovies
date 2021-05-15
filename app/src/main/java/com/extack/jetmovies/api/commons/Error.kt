package com.extack.jetmovies.api.commons

import com.extack.jetmovies.extensions.logInfo
import okhttp3.ResponseBody
import org.json.JSONObject

sealed class Error(val errorMessage: String) {

    data class HttpError(val code: Int, val message: String) : Error(message)

    data class NetworkError(val message: String) : Error(message)

    data class SocketTimeoutError(val message: String) : Error(message)

    data class UnknownError(val message: String) : Error(message)

}

private const val MESSAGE_KEY = "status_message"
private const val ERROR_KEY = "error"

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