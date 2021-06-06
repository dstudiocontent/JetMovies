package com.extack.jetmovies.extensions

import android.util.Log
import com.extack.jetmovies.BuildConfig

const val DEBUG_TAG = "_TAG"

fun logInfo(message: String? = "", throwable: Throwable? = null) {
    if (BuildConfig.DEBUG)
        Log.i(DEBUG_TAG, "$message", throwable)
}

fun logVerbose(message: String? = "", throwable: Throwable? = null) {
    if (BuildConfig.DEBUG)
        Log.v(DEBUG_TAG, "$message", throwable)
}

fun logWarn(message: String? = "", throwable: Throwable? = null) {
    if (BuildConfig.DEBUG)
        Log.w(DEBUG_TAG, "$message", throwable)
}

fun logDebug(message: String? = "", throwable: Throwable? = null) {
    if (BuildConfig.DEBUG)
        Log.d(DEBUG_TAG, "$message", throwable)
}

fun logError(message: String? = "", throwable: Throwable? = null) {
    if (BuildConfig.DEBUG)
        Log.e(DEBUG_TAG, "$message", throwable)
}