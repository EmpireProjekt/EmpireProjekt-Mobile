package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.status.tile

import android.content.Context
import androidx.wear.tiles.tooling.preview.Preview
import androidx.wear.tiles.tooling.preview.TilePreviewData
import androidx.wear.tiles.tooling.preview.TilePreviewHelper
import androidx.wear.tooling.preview.devices.WearDevices
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.status.component.WearStatusComponent

@OptIn(ExperimentalHorologistApi::class)
@Preview(device = WearDevices.SMALL_ROUND, name = "Small Round")
@Preview(device = WearDevices.LARGE_ROUND, name = "Large Round")
internal fun mainTilePreview(context: Context): TilePreviewData {
    val renderer = MainTileRenderer(
        context = context,
        statusesRowRenderer = StatusesRowRenderer(context = context),
        activityClassName = "MainActivity"
    )
    val state = WearStatusComponent.Model(
        loadingCount = 0,
        successCount = 0,
        failureCount = 0,
        updatedAt = null
    )
    return TilePreviewData { request ->
        TilePreviewHelper.singleTimelineEntryTileBuilder(
            renderer.renderTile(state, request.deviceConfiguration)
        ).build()
    }
}
