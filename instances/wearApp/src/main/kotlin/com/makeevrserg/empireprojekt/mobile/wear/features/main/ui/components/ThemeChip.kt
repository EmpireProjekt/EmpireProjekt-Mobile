package com.makeevrserg.empireprojekt.mobile.wear.features.main.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.R
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_bedtime
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_sunny
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.ComposeTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asPainter
import com.makeevrserg.empireprojekt.mobile.features.theme.data.model.Theme
import com.makeevrserg.empireprojekt.mobile.wear.features.components.IconTextChip

@Composable
fun ThemeChip(
    theme: Theme,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val icon = when (theme) {
        Theme.DARK -> MR.images.ic_bedtime
        Theme.LIGHT -> MR.images.ic_sunny
    }
    IconTextChip(
        text = stringResource(R.string.wear_main_switch_theme_action),
        painter = icon.asPainter(),
        modifier = modifier.fillMaxWidth(),
        iconColor = MaterialTheme.colors.onPrimary,
        onClick = onClick
    )
}

@Preview
@Composable
private fun ThemeChipDarkPreview() {
    AdaptThemeFade(composeTheme = ComposeTheme.DARK) {
        ThemeChip(theme = Theme.DARK, onClick = {})
    }
}

@Preview
@Composable
private fun ThemeChipLightPreview() {
    AdaptThemeFade(composeTheme = ComposeTheme.LIGHT) {
        ThemeChip(theme = Theme.LIGHT, onClick = {})
    }
}
