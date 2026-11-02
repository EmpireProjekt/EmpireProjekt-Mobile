package com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.api

import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.model.DecodedWearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.model.WearMessage
import kotlinx.coroutines.flow.Flow

interface WearMessageConsumer {
    val messagesFlow: Flow<DecodedWearMessage<*>>
    suspend fun <T> consume(message: WearMessage<T>, byteArray: ByteArray)
}
