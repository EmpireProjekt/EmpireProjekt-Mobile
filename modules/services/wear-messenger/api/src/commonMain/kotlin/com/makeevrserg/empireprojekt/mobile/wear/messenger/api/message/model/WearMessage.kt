package com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.model

import kotlinx.serialization.KSerializer

data class WearMessage<T>(
    val path: String,
    val serializer: KSerializer<T>
)
