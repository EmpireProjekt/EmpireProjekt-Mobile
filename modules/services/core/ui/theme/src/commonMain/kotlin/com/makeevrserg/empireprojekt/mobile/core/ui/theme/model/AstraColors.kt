package com.makeevrserg.empireprojekt.mobile.core.ui.theme.model

import androidx.compose.ui.graphics.Color

data class AstraColors(
    val surface: Surface,
    val action: Action,
    val astraLogo: AstraLogo,
    val isDark: Boolean
) {
    data class AstraLogo(
        val astraRed: Color,
        val astraBlue: Color,
        val astraOrange: Color,
        val astraYellow: Color,
    )

    data class Action(
        val colorNegative: Color,
        val colorPositive: Color,
    )

    data class Surface(
        val primary: Color,
        val onPrimary: Color,
        val primaryVariant: Color,
        val onPrimaryVariant: Color,
        val secondary: Color,
        val onSecondary: Color,
        val secondaryVariant: Color,
        val onSecondaryVariant: Color
    )
}
