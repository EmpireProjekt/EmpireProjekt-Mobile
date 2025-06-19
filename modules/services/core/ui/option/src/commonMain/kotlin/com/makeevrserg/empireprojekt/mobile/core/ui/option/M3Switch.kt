package com.makeevrserg.empireprojekt.mobile.core.ui.option

import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Composable
fun M3Switch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    Switch(
        modifier = modifier,
        checked = checked,
        onCheckedChange = onCheckedChange,
        enabled = enabled,
        colors = SwitchDefaults.colors(
            uncheckedBorderColor = Color.Transparent,
            uncheckedThumbColor = AppTheme.astraColors.surface.primaryVariant,
            uncheckedTrackColor = MaterialTheme.colors.onSecondary,
            checkedThumbColor = AppTheme.astraColors.surface.onSecondaryVariant,
            checkedBorderColor = Color.Transparent,
            checkedTrackColor = AppTheme.astraColors.surface.secondaryVariant,
            // todo no color for disabled in design system yet
            // checkedIconColor = TODO(),
            // disabledCheckedIconColor = TODO(),
            // disabledUncheckedIconColor = TODO(),
            // uncheckedIconColor = TODO(),
            disabledCheckedBorderColor = Color.Transparent,
//            disabledCheckedThumbColor = LocalCorruptedPallet.current.transparent.whiteInvert.tertiary,
//            disabledCheckedTrackColor = LocalCorruptedPallet.current.accent.device.primary,
            disabledUncheckedBorderColor = Color.Transparent,
//            disabledUncheckedThumbColor = LocalCorruptedPallet.current.transparent.whiteInvert.tertiary,
//            disabledUncheckedTrackColor = LocalCorruptedPallet.current.transparent.whiteInvert.tertiary,
        )
    )
}
