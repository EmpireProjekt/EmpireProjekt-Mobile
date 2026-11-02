package com.makeevrserg.empireprojekt.mobile.wear.messenger.api.status.util

import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.model.WearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.status.model.Status
import kotlinx.serialization.builtins.ListSerializer

val STATUS_WEAR_MESSAGE: WearMessage<List<Status>> = WearMessage(
    path = "/wear/statuses",
    serializer = ListSerializer(Status.serializer())
)
