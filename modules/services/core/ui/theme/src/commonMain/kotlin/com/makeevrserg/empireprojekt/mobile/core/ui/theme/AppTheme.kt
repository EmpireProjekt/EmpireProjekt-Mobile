package com.makeevrserg.empireprojekt.mobile.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

class AppTheme(
    val dimens: Dimens = Dimens(),
    val astraColors: AstraColors = AstraColors.Dark
) {
    companion object {
        val astraColors: AstraColors
            @Composable
            @ReadOnlyComposable
            get() = LocalAppTheme.current.astraColors
        val dimens: Dimens
            @Composable
            @ReadOnlyComposable
            get() = LocalAppTheme.current.dimens
    }
}
