package com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.model.AstraColors

@Composable
internal fun Material3ThemeAdaptComposable(colors: AstraColors, content: @Composable () -> Unit) {
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
