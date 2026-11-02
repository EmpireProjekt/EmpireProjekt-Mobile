package com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.model.ComposeTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.theme.AstraPalette
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.theme.LocalAppTheme

@Composable
fun AdaptThemeFadeComposable(
    composeTheme: ComposeTheme = ComposeTheme.DARK,
    content: @Composable () -> Unit
) {
    TransparentBarsComposable(composeTheme.isDark)
    val appTheme = when (composeTheme) {
        ComposeTheme.DARK -> AppTheme(astraColors = AstraPalette.Dark)
        ComposeTheme.LIGHT -> AppTheme(astraColors = AstraPalette.Light)
    }
    Crossfade(targetState = appTheme) { selectedTheme ->
        CompositionLocalProvider(
            LocalAppTheme provides selectedTheme,
            content = {
                Material2ThemeAdaptComposable(colors = selectedTheme.astraColors) {
                    Material3ThemeAdaptComposable(colors = selectedTheme.astraColors) {
                        content.invoke()
                    }
                }
            }
        )
    }
}
