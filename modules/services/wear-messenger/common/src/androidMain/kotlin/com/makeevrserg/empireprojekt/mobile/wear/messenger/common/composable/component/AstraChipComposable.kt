package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.composable.component

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Text
import com.makeevrserg.empireprojekt.mobile.core.ui.composable.AdaptThemeFadeComposable

@Composable
internal fun AstraChipComposable(
    label: @Composable RowScope.() -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: (@Composable BoxScope.() -> Unit)? = null
) {
    Chip(
        modifier = modifier,
        label = label,
        onClick = onClick,
        icon = icon,
        colors = ChipDefaults.chipColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            secondaryContentColor = MaterialTheme.colors.secondary,
            iconColor = MaterialTheme.colors.onPrimary,
            disabledBackgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.5f),
            disabledContentColor = MaterialTheme.colors.onPrimary.copy(alpha = 0.5f),
            disabledSecondaryContentColor = MaterialTheme.colors.secondary.copy(alpha = 0.5f),
            disabledIconColor = MaterialTheme.colors.onPrimary.copy(alpha = 0.5f)
        )
    )
}

@Preview
@Composable
private fun AstraChipPreview() {
    AdaptThemeFadeComposable {
        AstraChipComposable(
            label = { Text(text = "EmpireProjekt") },
            onClick = {}
        )
    }
}
