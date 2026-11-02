package com.makeevrserg.empireprojekt.mobile.wear.messenger.datalayer.message.model

internal class WearMessageDecodeError(path: String, cause: Throwable) :
    Exception("Could not decode the wear message $path", cause)
