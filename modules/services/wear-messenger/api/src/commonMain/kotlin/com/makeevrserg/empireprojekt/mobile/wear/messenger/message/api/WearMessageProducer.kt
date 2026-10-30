package com.makeevrserg.empireprojekt.mobile.wear.messenger.message.api

import com.makeevrserg.empireprojekt.mobile.wear.messenger.message.model.WearMessage

interface WearMessageProducer {
    suspend fun <T> produce(message: WearMessage<T>, value: T)
}
