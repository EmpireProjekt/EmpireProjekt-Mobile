package com.makeevrserg.empireprojekt.mobile.features.ui.towny.towns.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.placeholder.ShimmerCard
import com.makeevrserg.empireprojekt.mobile.core.ui.placeholder.ShimmerLine
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Suppress("MagicNumber")
@Composable
fun TownShimmerWidget(modifier: Modifier = Modifier) {
    ShimmerCard(modifier = modifier) {
        ShimmerLine(widthFraction = 0.4f)
        Spacer(Modifier.height(AppTheme.dimens.XXS))
        ShimmerLine(widthFraction = 0.3f)
        Spacer(Modifier.height(AppTheme.dimens.S))
        ShimmerLine(widthFraction = 0.6f)
        ShimmerLine(widthFraction = 0.5f)
        ShimmerLine(widthFraction = 0.8f)
    }
}

@Preview
@Composable
private fun TownShimmerWidgetPreview() {
    AdaptThemeFade {
        TownShimmerWidget()
    }
}
