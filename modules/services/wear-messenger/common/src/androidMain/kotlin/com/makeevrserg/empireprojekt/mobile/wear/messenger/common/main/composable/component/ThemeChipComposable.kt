package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.main.composable.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_bedtime
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_sunny
import com.makeevrserg.empireprojekt.mobile.core.resources.wear_main_switch_theme_action
import com.makeevrserg.empireprojekt.mobile.core.ui.common.util.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.common.util.asPainter
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.model.ComposeTheme
import com.makeevrserg.empireprojekt.mobile.features.theme.api.model.Theme
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.composable.component.IconTextChipComposable

@Composable
internal fun ThemeChipComposable(
    theme: Theme,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val icon = when (theme) {
        Theme.DARK -> MR.images.ic_bedtime
        Theme.LIGHT -> MR.images.ic_sunny
    }
    IconTextChipComposable(
        text = MR.strings.wear_main_switch_theme_action.asComposableString(),
        painter = icon.asPainter(),
        modifier = modifier.fillMaxWidth(),
        iconColor = MaterialTheme.colors.onPrimary,
        onClick = onClick
    )
}

@Preview
@Composable
private fun ThemeChipDarkPreview() {
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.DARK) {
        ThemeChipComposable(
            theme = Theme.DARK,
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun ThemeChipLightPreview() {
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.LIGHT) {
        ThemeChipComposable(
            theme = Theme.LIGHT,
            onClick = {}
        )
    }
}
