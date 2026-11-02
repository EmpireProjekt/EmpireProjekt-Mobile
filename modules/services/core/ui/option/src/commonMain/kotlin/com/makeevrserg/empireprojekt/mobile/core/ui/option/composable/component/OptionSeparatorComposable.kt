package com.makeevrserg.empireprojekt.mobile.core.ui.option.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.theme.AppTheme

@Composable
fun OptionSeparatorComposable(
    modifier: Modifier = Modifier,
    color: Color = AppTheme.astraColors.surface.onSecondary
) {
    Box(
        modifier
            .height(1.dp)
            .background(color)
    )
}

@Preview
@Composable
private fun OptionSeparatorPreview() {
    AdaptThemeFadeComposable {
        OptionSeparatorComposable(Modifier.fillMaxWidth())
    }
}
