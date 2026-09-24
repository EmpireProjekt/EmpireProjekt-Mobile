package com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.components

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
fun RatingUserShimmerWidget(modifier: Modifier = Modifier) {
    ShimmerCard(modifier = modifier) {
        ShimmerLine(widthFraction = 0.4f)
        Spacer(Modifier.height(AppTheme.dimens.S))
        ShimmerLine(widthFraction = 0.6f)
        ShimmerLine(widthFraction = 0.3f)
    }
}

@Preview
@Composable
private fun RatingUserShimmerWidgetPreview() {
    AdaptThemeFade {
        RatingUserShimmerWidget()
    }
}
