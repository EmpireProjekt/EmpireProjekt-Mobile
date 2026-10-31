package com.makeevrserg.empireprojekt.mobile.core.ui.option.composable.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Composable
fun M3SwitchComposable(
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
            disabledCheckedBorderColor = Color.Transparent,
            disabledUncheckedBorderColor = Color.Transparent,
        )
    )
}

@Preview
@Composable
private fun M3SwitchCheckedPreview() {
    AdaptThemeFadeComposable {
        M3SwitchComposable(
            checked = true,
            onCheckedChange = {},
            enabled = true
        )
    }
}

@Preview
@Composable
private fun M3SwitchUncheckedPreview() {
    AdaptThemeFadeComposable {
        M3SwitchComposable(
            checked = false,
            onCheckedChange = {},
            enabled = true
        )
    }
}

@Preview
@Composable
private fun M3SwitchDisabledPreview() {
    AdaptThemeFadeComposable {
        M3SwitchComposable(
            checked = true,
            onCheckedChange = {},
            enabled = false
        )
    }
}
