package com.makeevrserg.empireprojekt.mobile.wear.messenger.datalayer.message

import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.model.DecodedWearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.model.WearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.datalayer.message.model.WearMessageDecodeError
import kotlinx.serialization.json.Json

internal class WearMessageCodec(
    private val json: Json
) {
    fun <T> encode(message: WearMessage<T>, value: T): ByteArray {
        return json.encodeToString(message.serializer, value).encodeToByteArray()
    }

    fun <T> decode(message: WearMessage<T>, byteArray: ByteArray): Result<DecodedWearMessage<T>> {
        return runCatching { json.decodeFromString(message.serializer, byteArray.decodeToString()) }
            .fold(
                onSuccess = { value -> Result.success(DecodedWearMessage(path = message.path, value = value)) },
                onFailure = { error -> Result.failure(WearMessageDecodeError(message.path, error)) }
            )
    }
}
