package com.makeevrserg.empireprojekt.mobile.wear.messenger.message.model

data class DecodedWearMessage<T>(
    val path: String,
    val value: T
)
