package com.makeevrserg.empireprojekt.mobile.core.ui.placeholder.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.util.astraCard
import com.makeevrserg.empireprojekt.mobile.core.ui.util.placeholder

@Composable
fun ShimmerLineComposable(
    widthFraction: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .height(AppTheme.dimens.S)
            .fillMaxWidth(widthFraction)
            .placeholder(
                visible = true,
                color = MaterialTheme.colors.primaryVariant,
                highlightColor = MaterialTheme.colors.primary
            )
    )
}

@Composable
fun ShimmerCardComposable(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .astraCard(color = MaterialTheme.colors.primary)
            .padding(AppTheme.dimens.S),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS),
        content = content
    )
}

@Preview
@Composable
private fun ShimmerCardPreview() {
    AdaptThemeFadeComposable {
        ShimmerCardComposable {
            ShimmerLineComposable(widthFraction = 0.4f)
            ShimmerLineComposable(widthFraction = 0.6f)
            ShimmerLineComposable(widthFraction = 0.3f)
        }
    }
}
