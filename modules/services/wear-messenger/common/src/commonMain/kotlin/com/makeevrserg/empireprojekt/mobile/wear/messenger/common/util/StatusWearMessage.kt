package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.util

import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.model.Status
import com.makeevrserg.empireprojekt.mobile.wear.messenger.message.model.WearMessage
import kotlinx.serialization.builtins.ListSerializer

val STATUS_WEAR_MESSAGE: WearMessage<List<Status>> = WearMessage(
    path = "/wear/statuses",
    serializer = ListSerializer(Status.serializer())
)
