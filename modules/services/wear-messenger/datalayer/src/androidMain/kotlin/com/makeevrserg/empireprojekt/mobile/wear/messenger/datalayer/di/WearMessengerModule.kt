package com.makeevrserg.empireprojekt.mobile.wear.messenger.datalayer.di

import android.content.Context
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.data.WearDataLayerRegistry
import com.makeevrserg.empireprojekt.mobile.wear.messenger.datalayer.message.DataLayerWearMessageConsumer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.datalayer.message.DataLayerWearMessageProducer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.datalayer.message.WearMessageCodec
import com.makeevrserg.empireprojekt.mobile.wear.messenger.message.api.WearMessageConsumer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.message.api.WearMessageProducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json

interface WearMessengerModule {
    val wearMessageProducer: WearMessageProducer
    val wearMessageConsumer: WearMessageConsumer

    class Default(
        context: Context,
        coroutineScope: CoroutineScope,
    ) : WearMessengerModule {
        private val codec by lazy {
            val json = Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
            WearMessageCodec(json = json)
        }

        @OptIn(ExperimentalHorologistApi::class)
        private val wearDataLayerRegistry by lazy {
            WearDataLayerRegistry.fromContext(
                application = context,
                coroutineScope = coroutineScope
            )
        }

        @OptIn(ExperimentalHorologistApi::class)
        override val wearMessageProducer: WearMessageProducer by lazy {
            DataLayerWearMessageProducer(
                wearDataLayerRegistry = wearDataLayerRegistry,
                messageClient = wearDataLayerRegistry.messageClient,
                codec = codec
            )
        }

        override val wearMessageConsumer: WearMessageConsumer by lazy {
            DataLayerWearMessageConsumer(codec = codec)
        }
    }
}
