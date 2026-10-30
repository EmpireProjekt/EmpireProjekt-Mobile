package com.makeevrserg.empireprojekt.mobile.wear.messenger.message.fake

import com.makeevrserg.empireprojekt.mobile.wear.messenger.message.api.WearMessageConsumer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.message.model.DecodedWearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.message.model.WearMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

internal class FakeWearMessageConsumer : WearMessageConsumer {
    private val messages = MutableSharedFlow<DecodedWearMessage<*>>(extraBufferCapacity = 64)

    override val messagesFlow: Flow<DecodedWearMessage<*>> = messages

    override suspend fun <T> consume(message: WearMessage<T>, byteArray: ByteArray) {
        error("PingFeature never consumes raw messages")
    }

    fun sendMessage(message: DecodedWearMessage<*>) {
        messages.tryEmit(message)
    }
}
