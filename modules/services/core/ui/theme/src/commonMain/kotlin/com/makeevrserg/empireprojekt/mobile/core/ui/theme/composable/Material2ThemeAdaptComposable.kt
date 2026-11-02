package com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.theme.model.AstraColors

@Composable
internal fun Material2ThemeAdaptComposable(colors: AstraColors, content: @Composable () -> Unit) {
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
