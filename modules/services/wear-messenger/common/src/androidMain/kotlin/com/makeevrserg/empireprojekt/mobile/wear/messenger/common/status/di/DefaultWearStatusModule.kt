package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.status.di

import android.content.Context
import androidx.wear.protolayout.ActionBuilders
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.tiles.render.SingleTileLayoutRenderer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.api.WearMessageConsumer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.status.component.WearStatusComponent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.status.di.WearStatusModule
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.status.model.WearStatusState
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.status.component.DefaultWearStatusComponent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.status.tile.MainTileRenderer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.status.tile.StatusesRowRenderer
import kotlinx.coroutines.CoroutineScope
import kotlin.time.Clock

class DefaultWearStatusModule(
    wearMessageConsumer: WearMessageConsumer,
    coroutineScope: CoroutineScope,
    clock: Clock,
    context: Context
) : WearStatusModule {
    override val wearStatusComponent: WearStatusComponent by lazy {
        DefaultWearStatusComponent(
            wearMessageConsumer = wearMessageConsumer,
            coroutineScope = coroutineScope,
            clock = clock
        )
    }

    @OptIn(ExperimentalHorologistApi::class)
    val mainTileRenderer: SingleTileLayoutRenderer<WearStatusState, WearStatusState> by lazy {
        val launchAction = context.packageManager.getLaunchIntentForPackage(context.packageName)
            ?.component
            ?.let(ActionBuilders::launchAction)
        MainTileRenderer(
            context = context,
            statusesRowRenderer = StatusesRowRenderer(context = context),
            launchAction = launchAction
        )
    }
}
