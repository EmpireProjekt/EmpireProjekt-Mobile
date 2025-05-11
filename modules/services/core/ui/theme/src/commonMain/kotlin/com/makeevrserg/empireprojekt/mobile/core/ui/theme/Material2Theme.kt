package com.makeevrserg.empireprojekt.mobile.core.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.Shapes
import androidx.compose.material.Typography

internal data class Material2Theme(
    val colors: Colors,
    val typography: Typography = Typography(),
    val shapes: Shapes = Shapes(),
)

internal fun AstraColors.asMaterial2(): Colors {
    return Colors(
        isLight = !isDark,
        primary = surface.primary,
        primaryVariant = surface.primaryVariant,
        secondary = surface.secondary,
        secondaryVariant = surface.secondaryVariant,
        background = surface.primaryVariant,
        surface = surface.onPrimary,
        error = action.colorNegative,
        onPrimary = surface.onPrimary,
        onSecondary = surface.onSecondary,
        onBackground = surface.onPrimaryVariant,
        onSurface = surface.primary,
        onError = surface.onPrimary,
    )
}

internal fun AppTheme.asMaterial2(): Material2Theme {
    return Material2Theme(
        colors = astraColors.asMaterial2(),
    )
}
