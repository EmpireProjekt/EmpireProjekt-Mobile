package com.makeevrserg.empireprojekt.mobile.core.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

internal data class Material3Theme(
    val colorScheme: ColorScheme,
    val typography: Typography = Typography(),
    val shapes: Shapes = Shapes(),
)

internal fun AstraColors.asMaterial3(): ColorScheme {
    val defaultColorScheme = when (isDark) {
        true -> darkColorScheme()
        false -> lightColorScheme()
    }
    return ColorScheme(
        primary = surface.primary,
        onPrimary = surface.onPrimary,
        primaryContainer = surface.primaryVariant,
        onPrimaryContainer = defaultColorScheme.onPrimaryContainer,
        inversePrimary = defaultColorScheme.inversePrimary,
        secondary = surface.secondary,
        onSecondary = surface.onSecondary,
        secondaryContainer = surface.secondaryVariant,
        onSecondaryContainer = surface.onSecondaryVariant,
        tertiary = defaultColorScheme.tertiary,
        onTertiary = defaultColorScheme.onTertiary,
        tertiaryContainer = defaultColorScheme.tertiaryContainer,
        onTertiaryContainer = defaultColorScheme.onTertiaryContainer,
        background = surface.primaryVariant,
        onBackground = defaultColorScheme.onBackground,
        surface = surface.primaryVariant,
        onSurface = surface.onPrimaryVariant,
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
}

internal fun AppTheme.asMaterial3(): Material3Theme {
    return Material3Theme(
        colorScheme = astraColors.asMaterial3(),
    )
}
