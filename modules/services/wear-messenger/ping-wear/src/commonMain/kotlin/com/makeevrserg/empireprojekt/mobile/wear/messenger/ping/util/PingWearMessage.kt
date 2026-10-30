package com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.util

import com.makeevrserg.empireprojekt.mobile.wear.messenger.message.model.WearMessage
import kotlinx.serialization.builtins.serializer
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

val PING_WEAR_MESSAGE: WearMessage<Byte> = WearMessage(
    path = "/wear/ping",
    serializer = Byte.serializer()
)

val PING_INTERVAL: Duration = 5.seconds

val PING_TIMEOUT: Duration = 20.seconds
