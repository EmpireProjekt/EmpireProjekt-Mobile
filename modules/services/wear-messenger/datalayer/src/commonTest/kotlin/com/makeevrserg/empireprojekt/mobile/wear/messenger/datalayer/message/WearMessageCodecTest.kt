package com.makeevrserg.empireprojekt.mobile.wear.messenger.datalayer.message

import com.makeevrserg.empireprojekt.mobile.wear.messenger.datalayer.message.model.WearMessageDecodeError
import com.makeevrserg.empireprojekt.mobile.wear.messenger.message.model.DecodedWearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.message.model.WearMessage
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class WearMessageCodecTest {
    private val codec = WearMessageCodec(Json { ignoreUnknownKeys = true })
    private val namesMessage = WearMessage(path = "/wear/names", serializer = ListSerializer(String.serializer()))

    @Test
    fun GIVEN_value_WHEN_encoded_and_decoded_THEN_same_value_comes_back_with_message_path() {
        val names = listOf("Steve", "Alex")

        val byteArray = codec.encode(namesMessage, names)
        val decoded = codec.decode(namesMessage, byteArray)

        assertEquals(
            expected = DecodedWearMessage(path = "/wear/names", value = names),
            actual = decoded.getOrThrow()
        )
    }

    @Test
    fun GIVEN_malformed_bytes_WHEN_decoded_THEN_fails_with_decode_error() {
        val decoded = codec.decode(namesMessage, "not json".encodeToByteArray())

        assertIs<WearMessageDecodeError>(decoded.exceptionOrNull())
    }

    @Test
    fun GIVEN_bytes_of_another_type_WHEN_decoded_THEN_fails_with_decode_error() {
        val decoded = codec.decode(namesMessage, "42".encodeToByteArray())

        assertIs<WearMessageDecodeError>(decoded.exceptionOrNull())
    }
}
