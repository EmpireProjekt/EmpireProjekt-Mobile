package com.makeevrserg.empireprojekt.mobile.core.ui.theme.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.model.AstraColors

class AppTheme(val astraColors: AstraColors = AstraPalette.Dark) {
    companion object {
        val dimens: Dimens = Dimens

        val astraColors: AstraColors
            @Composable
            @ReadOnlyComposable
            get() = LocalAppTheme.current.astraColors
    }
}
