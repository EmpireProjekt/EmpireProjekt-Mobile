package com.makeevrserg.empireprojekt.mobile.core.log.util

import android.util.Log

internal fun writeLogcatError(tag: String, message: String, error: Throwable?) {
    Log.e(
        tag,
        message,
        error
    )
}

internal fun writeLogcatInfo(tag: String, message: String) {
    Log.i(tag, message)
}

internal fun writeLogcatVerbose(tag: String, message: String) {
    Log.v(tag, message)
}

internal fun writeLogcatWarn(tag: String, message: String) {
    Log.w(tag, message)
}

internal fun writeLogcatDebug(tag: String, message: String) {
    Log.d(tag, message)
}
