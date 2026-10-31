package com.makeevrserg.empireprojekt.mobile.features.ui.rating.userlist.composable.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.placeholder.composable.ShimmerCardComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.placeholder.composable.ShimmerLineComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Suppress("MagicNumber")
@Composable
fun RatingUserShimmerWidgetComposable(modifier: Modifier = Modifier) {
    ShimmerCardComposable(modifier = modifier) {
        ShimmerLineComposable(widthFraction = 0.4f)
        Spacer(Modifier.height(AppTheme.dimens.S))
        ShimmerLineComposable(widthFraction = 0.6f)
        ShimmerLineComposable(widthFraction = 0.3f)
    }
}

@Preview
@Composable
private fun RatingUserShimmerWidgetPreview() {
    AdaptThemeFadeComposable {
        RatingUserShimmerWidgetComposable()
    }
}
