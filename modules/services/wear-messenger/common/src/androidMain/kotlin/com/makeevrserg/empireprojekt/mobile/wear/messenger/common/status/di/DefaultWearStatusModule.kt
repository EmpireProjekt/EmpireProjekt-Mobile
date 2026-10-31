package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.status.di

import android.content.Context
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.api.WearMessageConsumer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.status.component.WearStatusComponent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.status.di.WearStatusModule
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.status.component.DefaultWearStatusComponent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.status.tile.MainTileRenderer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.status.tile.StatusesRowRenderer
import kotlinx.coroutines.CoroutineScope
import kotlin.time.Clock

class DefaultWearStatusModule(
    wearMessageConsumer: WearMessageConsumer,
    coroutineScope: CoroutineScope,
    clock: Clock,
    context: Context,
    activityClassName: String
) : WearStatusModule {
    override val wearStatusComponent: WearStatusComponent by lazy {
        DefaultWearStatusComponent(
            wearMessageConsumer = wearMessageConsumer,
            coroutineScope = coroutineScope,
            clock = clock
        )
    }

    val mainTileRenderer: MainTileRenderer by lazy {
        MainTileRenderer(
            context = context,
            statusesRowRenderer = StatusesRowRenderer(context = context),
            activityClassName = activityClassName
        )
    }
}
