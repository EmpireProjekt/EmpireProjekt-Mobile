package com.makeevrserg.empireprojekt.mobile.wear.messenger.datalayer.message

import android.util.Log
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.wearable.MessageClient
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.data.WearDataLayerRegistry
import com.makeevrserg.empireprojekt.mobile.wear.messenger.message.api.WearMessageProducer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.message.model.WearMessage
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalHorologistApi::class)
internal class DataLayerWearMessageProducer(
    private val wearDataLayerRegistry: WearDataLayerRegistry,
    private val messageClient: MessageClient,
    private val codec: WearMessageCodec
) : WearMessageProducer {

    private suspend fun sendToConnectedNodes(path: String, byteArray: ByteArray) {
        coroutineScope {
            val nodes = wearDataLayerRegistry.nodeClient.connectedNodes.await()
            Log.d(TAG, "produce: found ${nodes.size} nodes")
            nodes.map { node ->
                async {
                    messageClient.sendMessage(
                        node.id,
                        path,
                        byteArray
                    )
                }
            }.awaitAll()
        }
    }

    override suspend fun <T> produce(message: WearMessage<T>, value: T) {
        val byteArray = codec.encode(message, value)
        try {
            sendToConnectedNodes(message.path, byteArray)
            Log.d(TAG, "produce: message sent ${message.path}")
        } catch (error: ApiException) {
            Log.e(
                TAG,
                "produce: failed to send message ${message.path}",
                error
            )
        }
    }

    private companion object {
        const val TAG = "WearMessageProducer"
    }
}
