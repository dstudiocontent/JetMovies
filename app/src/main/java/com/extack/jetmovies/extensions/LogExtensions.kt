package com.extack.jetmovies.extensions

import android.util.Log
import com.extack.jetmovies.BuildConfig

const val DEBUG_TAG = "_TAG"

fun Any.logInfo(message: String? = "", throwable: Throwable? = null) {
    if (BuildConfig.DEBUG)
        Log.i(DEBUG_TAG, "${this.javaClass.simpleName} $message", throwable)
}

fun Any.logVerbose(message: String? = "", throwable: Throwable? = null) {
    if (BuildConfig.DEBUG)
        Log.v(DEBUG_TAG, "${this.javaClass.simpleName} $message", throwable)
}

fun Any.logWarn(message: String? = "", throwable: Throwable? = null) {
    if (BuildConfig.DEBUG)
        Log.w(DEBUG_TAG, "${this.javaClass.simpleName} $message", throwable)
}

fun Any.logDebug(message: String? = "", throwable: Throwable? = null) {
    if (BuildConfig.DEBUG)
        Log.d(DEBUG_TAG, "${this.javaClass.simpleName} $message", throwable)
}

fun Any.logError(message: String? = "", throwable: Throwable? = null) {
    if (BuildConfig.DEBUG)
        Log.e(DEBUG_TAG, "${this.javaClass.simpleName} $message", throwable)
}