package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.status.tile

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.wear.protolayout.ActionBuilders
import androidx.wear.protolayout.DeviceParametersBuilders
import androidx.wear.protolayout.DimensionBuilders
import androidx.wear.protolayout.LayoutElementBuilders
import androidx.wear.protolayout.ModifiersBuilders
import androidx.wear.protolayout.material.Button
import androidx.wear.protolayout.material.ButtonColors
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.tiles.render.SingleTileLayoutRenderer
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.status.model.WearStatusState
import java.util.UUID

@OptIn(ExperimentalHorologistApi::class)
internal class StatusesRowRenderer(
    context: Context
) : SingleTileLayoutRenderer<WearStatusState, WearStatusState>(context) {

    private fun statusButton(
        amount: Int,
        accentColor: Color
    ): LayoutElementBuilders.LayoutElement {
        return Button.Builder(
            context,
            ModifiersBuilders.Clickable.Builder()
                .setId(UUID.randomUUID().toString())
                .setOnClick(ActionBuilders.LoadAction.Builder().build())
                .build()
        ).setButtonColors(
            ButtonColors(
                theme.surface,
                accentColor.toArgb()
            )
        ).setTextContent(amount.toString()).build()
    }

    @Suppress("MagicNumber")
    override fun renderTile(
        state: WearStatusState,
        deviceParameters: DeviceParametersBuilders.DeviceParameters
    ): LayoutElementBuilders.LayoutElement {
        return LayoutElementBuilders.Row.Builder()
            .setWidth(DimensionBuilders.wrap())
            .setVerticalAlignment(LayoutElementBuilders.VERTICAL_ALIGN_CENTER)
            .addContent(
                statusButton(
                    amount = state.successCount,
                    accentColor = AppTheme().astraColors.action.colorPositive
                )
            )
            .addContent(
                LayoutElementBuilders.Box.Builder().setWidth(DimensionBuilders.dp(8f)).setHeight(
                    DimensionBuilders.expand()
                ).build()
            )
            .addContent(
                statusButton(
                    amount = state.loadingCount,
                    accentColor = AppTheme().astraColors.astraLogo.astraOrange
                )
            )
            .addContent(
                LayoutElementBuilders.Box.Builder().setWidth(DimensionBuilders.dp(8f)).setHeight(
                    DimensionBuilders.expand()
                ).build()
            )
            .addContent(
                statusButton(
                    amount = state.failureCount,
                    accentColor = AppTheme().astraColors.action.colorNegative
                )
            ).build()
    }
}
