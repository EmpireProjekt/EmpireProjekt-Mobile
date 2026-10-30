package com.makeevrserg.empireprojekt.mobile.wear.messenger.message.api

import com.makeevrserg.empireprojekt.mobile.wear.messenger.message.model.DecodedWearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.message.model.WearMessage
import kotlinx.coroutines.flow.Flow

interface WearMessageConsumer {
    val messagesFlow: Flow<DecodedWearMessage<*>>
    suspend fun <T> consume(message: WearMessage<T>, byteArray: ByteArray)
}
