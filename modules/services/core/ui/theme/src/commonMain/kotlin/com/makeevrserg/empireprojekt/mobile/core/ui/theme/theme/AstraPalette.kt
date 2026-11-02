package com.makeevrserg.empireprojekt.mobile.core.ui.theme.theme

import androidx.compose.ui.graphics.Color
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.model.AstraColors

object AstraPalette {
    private val LogoColors = AstraColors.AstraLogo(
        astraRed = Color(color = 0xFFBC2551),
        astraBlue = Color(color = 0xFF304D7B),
        astraOrange = Color(color = 0xFFD34829),
        astraYellow = Color(color = 0xFFDA8D2C),
    )

    private val ActionColors = AstraColors.Action(
        colorNegative = Color(color = 0xFF8D2E2E),
        colorPositive = Color(color = 0xFF3C7C42)
    )

    val Dark = AstraColors(
        isDark = true,
        surface = AstraColors.Surface(
            primary = Color(color = 0xFF1F252C),
            onPrimary = Color(color = 0xFFFFFEFD),
            primaryVariant = Color(color = 0xFF151C1F),
            onPrimaryVariant = Color(color = 0xFFFFFEFD),
            secondary = Color(color = 0xFFFFC100),
            onSecondary = Color(color = 0xFF59626D),
            secondaryVariant = Color(color = 0xFF1B76CA),
            onSecondaryVariant = Color(color = 0xFFFFFEFD)
        ),
        action = ActionColors,
        astraLogo = LogoColors
    )

    val Light = AstraColors(
        isDark = false,
        surface = AstraColors.Surface(
            primary = Color(color = 0xFFFFFFFF),
            onPrimary = Color(color = 0xFF181818),
            primaryVariant = Color(color = 0xFFF1F1F1),
            onPrimaryVariant = Color(color = 0xFFFFFFFF),
            secondary = Color(color = 0xFFFFC100),
            onSecondary = Color(color = 0xFF4E5C66),
            secondaryVariant = Color(color = 0xFF1B76CA),
            onSecondaryVariant = Color(color = 0xFFFFFFFF)
        ),
        action = ActionColors,
        astraLogo = LogoColors
    )
}
