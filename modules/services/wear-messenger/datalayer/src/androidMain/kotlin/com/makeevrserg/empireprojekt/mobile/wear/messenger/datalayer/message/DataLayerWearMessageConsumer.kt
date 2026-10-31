package com.makeevrserg.empireprojekt.mobile.wear.messenger.datalayer.message

import android.util.Log
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.api.WearMessageConsumer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.model.DecodedWearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.model.WearMessage
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

internal class DataLayerWearMessageConsumer(
    private val codec: WearMessageCodec
) : WearMessageConsumer {
    private val messageChannel = Channel<DecodedWearMessage<*>>()
    override val messagesFlow: Flow<DecodedWearMessage<*>> = messageChannel.receiveAsFlow()

    override suspend fun <T> consume(message: WearMessage<T>, byteArray: ByteArray) {
        val decodedWearMessage = codec.decode(message, byteArray).getOrElse { error ->
            Log.e(
                TAG,
                "consume: could not publish message",
                error
            )
            return
        }
        messageChannel.send(decodedWearMessage)
        Log.d(TAG, "consume: published message ${message.path}")
    }

    private companion object {
        const val TAG = "WearMessageConsumer"
    }
}
