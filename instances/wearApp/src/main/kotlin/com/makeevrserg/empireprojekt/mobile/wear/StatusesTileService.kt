package com.makeevrserg.empireprojekt.mobile.wear

import androidx.lifecycle.lifecycleScope
import androidx.wear.protolayout.LayoutElementBuilders
import androidx.wear.protolayout.ResourceBuilders
import androidx.wear.protolayout.TimelineBuilders
import androidx.wear.tiles.RequestBuilders
import androidx.wear.tiles.TileBuilders
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.tiles.SuspendingTileService
import com.makeevrserg.empireprojekt.mobile.modules.services.core.resources.R
import com.makeevrserg.empireprojekt.mobile.wear.App.Companion.asEmpireApp
import com.makeevrserg.empireprojekt.mobile.wear.di.WearRootModule
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.status.component.WearStatusComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

private const val RESOURCES_VERSION = "1"

@OptIn(ExperimentalHorologistApi::class)
class StatusesTileService : SuspendingTileService() {

    private val wearRootModule: WearRootModule
        get() = application.asEmpireApp().wearRootModule
    private val wearStatusComponent: WearStatusComponent
        get() = wearRootModule.wearStatusModule.wearStatusComponent

    override fun onCreate() {
        super.onCreate()

        lifecycleScope.launch {
            while (isActive) {
                delay(timeMillis = 1000L)
                getUpdater(applicationContext).requestUpdate(StatusesTileService::class.java)
            }
        }
    }

    override suspend fun resourcesRequest(
        requestParams: RequestBuilders.ResourcesRequest
    ): ResourceBuilders.Resources {
        return ResourceBuilders.Resources.Builder()
            .setVersion(RESOURCES_VERSION)
            .addIdToImageMapping(
                requireNotNull(R.drawable.ic_esmptelegram::class.simpleName),
                ResourceBuilders.ImageResource.Builder()
                    .setAndroidResourceByResId(
                        ResourceBuilders.AndroidImageResourceByResId.Builder()
                            .setResourceId(R.drawable.ic_esmptelegram)
                            .build()
                    ).build()
            )
            .build()
    }

    @Suppress("MagicNumber")
    override suspend fun tileRequest(
        requestParams: RequestBuilders.TileRequest
    ): TileBuilders.Tile {
        val state = wearStatusComponent.mergedState.value
        val layout = LayoutElementBuilders.Layout.Builder()
            .setRoot(
                wearRootModule.wearStatusModule.mainTileRenderer.renderTile(
                    state,
                    requestParams.deviceConfiguration
                )
            )
            .build()
        val entry = TimelineBuilders.TimelineEntry.Builder()
            .setLayout(layout)
            .build()
        val singleTileTimeline = TimelineBuilders.Timeline.Builder()
            .addTimelineEntry(entry)
            .build()
        return TileBuilders.Tile.Builder()
            .setResourcesVersion(RESOURCES_VERSION)
            .setFreshnessIntervalMillis(1000L)
            .setTileTimeline(singleTileTimeline)
            .build()
    }
}
