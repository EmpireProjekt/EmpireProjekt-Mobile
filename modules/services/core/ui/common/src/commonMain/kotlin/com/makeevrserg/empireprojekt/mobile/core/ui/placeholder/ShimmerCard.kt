package com.makeevrserg.empireprojekt.mobile.core.ui.placeholder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.common.astraCard
import com.makeevrserg.empireprojekt.mobile.core.ui.common.placeholder
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

/**
 * Shimmering bar standing in for one line of text while content loads.
 *
 * @param widthFraction share of the available width in the range 0.0..1.0
 */
@Composable
fun ShimmerLine(
    widthFraction: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .height(AppTheme.dimens.S)
            .fillMaxWidth(widthFraction)
            .placeholder(true)
    )
}

/**
 * Card surface for [ShimmerLine]s, shaped like the list cards it replaces while loading.
 */
@Composable
fun ShimmerCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .astraCard()
            .padding(AppTheme.dimens.S),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS),
        content = content
    )
}

@Suppress("MagicNumber")
@Preview
@Composable
private fun ShimmerCardPreview() {
    AdaptThemeFade {
        ShimmerCard {
            ShimmerLine(widthFraction = 0.4f)
            ShimmerLine(widthFraction = 0.6f)
            ShimmerLine(widthFraction = 0.3f)
        }
    }
}
