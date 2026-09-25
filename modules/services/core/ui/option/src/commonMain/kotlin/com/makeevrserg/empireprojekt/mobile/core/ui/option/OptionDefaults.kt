package com.makeevrserg.empireprojekt.mobile.core.ui.option

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

object OptionDefaults {
    val ContentPadding: PaddingValues
        @Composable
        @ReadOnlyComposable
        get() = PaddingValues(AppTheme.dimens.XS)
}
