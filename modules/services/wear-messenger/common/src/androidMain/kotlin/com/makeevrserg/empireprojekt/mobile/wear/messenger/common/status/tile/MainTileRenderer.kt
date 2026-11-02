package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.status.tile

import android.content.Context
import androidx.wear.protolayout.ActionBuilders
import androidx.wear.protolayout.DeviceParametersBuilders
import androidx.wear.protolayout.DimensionBuilders
import androidx.wear.protolayout.LayoutElementBuilders
import androidx.wear.protolayout.ModifiersBuilders
import androidx.wear.protolayout.material.ChipColors
import androidx.wear.protolayout.material.CompactChip
import androidx.wear.protolayout.material.Text
import androidx.wear.protolayout.material.Typography
import androidx.wear.protolayout.material.layouts.PrimaryLayout
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.tiles.render.SingleTileLayoutRenderer
import com.makeevrserg.empireprojekt.mobile.modules.services.core.resources.R
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.status.model.WearStatusState
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.util.asColorProp
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.util.formatClockTime

@OptIn(ExperimentalHorologistApi::class)
internal class MainTileRenderer(
    context: Context,
    private val statusesRowRenderer: StatusesRowRenderer,
    private val launchAction: ActionBuilders.LaunchAction?
) : SingleTileLayoutRenderer<WearStatusState, WearStatusState>(context) {
    @Suppress("LongMethod", "MagicNumber")
    private fun tileLayout(
        state: WearStatusState,
        deviceParameters: DeviceParametersBuilders.DeviceParameters
    ): LayoutElementBuilders.LayoutElement {
        val image = LayoutElementBuilders.Image.Builder()
            .setWidth(DimensionBuilders.dp(24f))
            .setHeight(DimensionBuilders.dp(24f))
            .setResourceId(requireNotNull(R.drawable.ic_esmptelegram::class.simpleName))
            .setModifiers(
                ModifiersBuilders.Modifiers.Builder()
                    .setSemantics(
                        ModifiersBuilders.Semantics.Builder()
                            .setContentDescription("Image description")
                            .build()
                    )
                    .build()
            ).build()
        val text = Text.Builder(context, "Empire Network Status")
            .setTypography(Typography.TYPOGRAPHY_CAPTION1)
            .setColor(theme.onSurface.asColorProp)
            .setModifiers(
                ModifiersBuilders.Modifiers.Builder()
                    .setClickable(
                        ModifiersBuilders.Clickable.Builder()
                            .setId("reload")
                            .setOnClick(ActionBuilders.LoadAction.Builder().build())
                            .build()
                    ).build()
            )
            .build()

        val textTimeStamp = Text.Builder(context, state.updatedAt.formatClockTime())
            .setTypography(Typography.TYPOGRAPHY_CAPTION1)
            .setColor(theme.onSurface.asColorProp)
            .setModifiers(
                ModifiersBuilders.Modifiers.Builder()
                    .setClickable(
                        ModifiersBuilders.Clickable.Builder()
                            .setId("reload")
                            .setOnClick(ActionBuilders.LoadAction.Builder().build())
                            .build()
                    ).build()
            )
            .build()
        val statuses = statusesRowRenderer.renderTile(state, deviceParameters)

        val column = LayoutElementBuilders.Column.Builder()
            .setHorizontalAlignment(LayoutElementBuilders.HORIZONTAL_ALIGN_CENTER)
            .setWidth(DimensionBuilders.expand())
            .addContent(image)
            .addContent(text)
            .addContent(textTimeStamp)
            .addContent(statuses)
            .build()

        val layout = PrimaryLayout.Builder(deviceParameters).setContent(column)
        if (launchAction != null) {
            val compactChip = CompactChip.Builder(
                context,
                "More",
                ModifiersBuilders.Clickable.Builder()
                    .setId("openmain")
                    .setOnClick(launchAction)
                    .build(),
                deviceParameters
            ).setChipColors(ChipColors(theme.surface, theme.onSurface)).build()
            layout.setPrimaryChipContent(compactChip)
        }
        return layout.build()
    }

    override fun renderTile(
        state: WearStatusState,
        deviceParameters: DeviceParametersBuilders.DeviceParameters
    ): LayoutElementBuilders.LayoutElement {
        return tileLayout(state, deviceParameters)
    }
}
