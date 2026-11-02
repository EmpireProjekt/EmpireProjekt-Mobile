package com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.api

import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.model.WearMessage

interface WearMessageProducer {
    suspend fun <T> produce(message: WearMessage<T>, value: T)
}
