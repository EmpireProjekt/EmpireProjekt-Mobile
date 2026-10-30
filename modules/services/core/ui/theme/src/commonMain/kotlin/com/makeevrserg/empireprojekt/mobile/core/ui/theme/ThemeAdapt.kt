package com.makeevrserg.empireprojekt.mobile.core.ui.theme

import androidx.compose.animation.Crossfade
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.model.AstraColors
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.model.ComposeTheme

internal val LocalAppTheme: ProvidableCompositionLocal<AppTheme> = compositionLocalOf {
    AppTheme()
}

@Composable
private fun Material3ThemeAdapt(colors: AstraColors, content: @Composable () -> Unit) {
    val defaultColorScheme = when (colors.isDark) {
        true -> darkColorScheme()
        false -> lightColorScheme()
    }
    val colorScheme = ColorScheme(
        primary = colors.surface.primary,
        onPrimary = colors.surface.onPrimary,
        primaryContainer = colors.surface.primaryVariant,
        onPrimaryContainer = defaultColorScheme.onPrimaryContainer,
        inversePrimary = defaultColorScheme.inversePrimary,
        secondary = colors.surface.secondary,
        onSecondary = colors.surface.onSecondary,
        secondaryContainer = colors.surface.secondaryVariant,
        onSecondaryContainer = colors.surface.onSecondaryVariant,
        tertiary = defaultColorScheme.tertiary,
        onTertiary = defaultColorScheme.onTertiary,
        tertiaryContainer = defaultColorScheme.tertiaryContainer,
        onTertiaryContainer = defaultColorScheme.onTertiaryContainer,
        background = colors.surface.primaryVariant,
        onBackground = defaultColorScheme.onBackground,
        surface = colors.surface.primaryVariant,
        onSurface = colors.surface.onPrimaryVariant,
        surfaceVariant = defaultColorScheme.surfaceVariant,
        onSurfaceVariant = defaultColorScheme.onSurfaceVariant,
        surfaceTint = defaultColorScheme.surfaceTint,
        inverseSurface = defaultColorScheme.inverseSurface,
        inverseOnSurface = defaultColorScheme.inverseOnSurface,
        error = defaultColorScheme.error,
        onError = defaultColorScheme.onError,
        errorContainer = defaultColorScheme.errorContainer,
        onErrorContainer = defaultColorScheme.onErrorContainer,
        outline = defaultColorScheme.outline,
        outlineVariant = defaultColorScheme.outlineVariant,
        scrim = defaultColorScheme.scrim,
        surfaceBright = defaultColorScheme.surfaceBright,
        surfaceDim = defaultColorScheme.surfaceDim,
        surfaceContainer = defaultColorScheme.surfaceContainer,
        surfaceContainerHigh = defaultColorScheme.surfaceContainerHigh,
        surfaceContainerHighest = defaultColorScheme.surfaceContainerHighest,
        surfaceContainerLow = defaultColorScheme.surfaceContainerLow,
        surfaceContainerLowest = defaultColorScheme.surfaceContainerLowest
    )
    androidx.compose.material3.MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}

@Composable
private fun Material2ThemeAdapt(colors: AstraColors, content: @Composable () -> Unit) {
    val materialColors = Colors(
        isLight = !colors.isDark,
        primary = colors.surface.primary,
        primaryVariant = colors.surface.primaryVariant,
        secondary = colors.surface.secondary,
        secondaryVariant = colors.surface.secondaryVariant,
        background = colors.surface.primaryVariant,
        surface = colors.surface.onPrimary,
        error = colors.action.colorNegative,
        onPrimary = colors.surface.onPrimary,
        onSecondary = colors.surface.onSecondary,
        onBackground = colors.surface.onPrimaryVariant,
        onSurface = colors.surface.primary,
        onError = colors.surface.onPrimary,
    )
    MaterialTheme(
        colors = materialColors,
        content = content
    )
}

@Composable
fun AdaptThemeFade(
    composeTheme: ComposeTheme = ComposeTheme.DARK,
    content: @Composable () -> Unit
) {
    TransparentBars(composeTheme.isDark)
    val appTheme = when (composeTheme) {
        ComposeTheme.DARK -> AppTheme(astraColors = AstraPalette.Dark)
        ComposeTheme.LIGHT -> AppTheme(astraColors = AstraPalette.Light)
    }
    Crossfade(targetState = appTheme) { selectedTheme ->
        CompositionLocalProvider(
            LocalAppTheme provides selectedTheme,
            content = {
                Material2ThemeAdapt(colors = selectedTheme.astraColors) {
                    Material3ThemeAdapt(colors = selectedTheme.astraColors) {
                        content.invoke()
                    }
                }
            }
        )
    }
}
