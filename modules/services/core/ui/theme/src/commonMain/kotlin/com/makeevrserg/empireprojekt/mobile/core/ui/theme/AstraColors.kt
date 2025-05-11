package com.makeevrserg.empireprojekt.mobile.core.ui.theme

import androidx.compose.ui.graphics.Color
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AstraColors.Action
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AstraColors.AstraLogo
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AstraColors.Surface

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

    companion object
}

internal val AstraColors.Companion.Dark: AstraColors
    get() = AstraColors(
        isDark = true,
        surface = Surface(
            primary = Color(0xFF1F252C),
            onPrimary = Color(0xFFFFFEFD),
            primaryVariant = Color(0xFF151C1F),
            onPrimaryVariant = Color(0xFFFFFEFD),
            secondary = Color(0xFFFFC100),
            onSecondary = Color(0xFF59626D),
            secondaryVariant = Color(0xFF1B76CA),
            onSecondaryVariant = Color(0xFFFFFEFD)
        ),
        action = Action(
            colorNegative = Color(0xFF8D2E2E),
            colorPositive = Color(0xFF3C7C42)
        ),
        astraLogo = AstraLogo(
            astraRed = Color(0xFFbc2551),
            astraBlue = Color(0xFF304d7b),
            astraOrange = Color(0xFFd34829),
            astraYellow = Color(0xFFDA8D2C),
        )
    )

internal val AstraColors.Companion.Light: AstraColors
    get() = AstraColors(
        isDark = false,
        surface = Surface(
            primary = Color(0xFFFFFFFF),
            onPrimary = Color(0xFF181818),
            primaryVariant = Color(0xFFF1F1F1),
            onPrimaryVariant = Color(0xFFFFFFFF),
            secondary = Color(0xFFFFC100),
            onSecondary = Color(0xFF4E5C66),
            secondaryVariant = Color(0xFF1B76CA),
            onSecondaryVariant = Color(0xFFFFFFFF)
        ),
        action = Action(
            colorNegative = Color(0xFF8D2E2E),
            colorPositive = Color(0xFF3C7C42)
        ),
        astraLogo = AstraLogo(
            astraRed = Color(0xFFbc2551),
            astraBlue = Color(0xFF304d7b),
            astraOrange = Color(0xFFd34829),
            astraYellow = Color(0xFFDA8D2C),
        )
    )
