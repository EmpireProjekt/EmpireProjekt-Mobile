package com.makeevrserg.empireprojekt.mobile.core.ui.filtercard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.common.astraCard
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Composable
fun FilterCard(
    modifier: Modifier = Modifier,
    content: @Composable FilterCardScope.() -> Unit
) {
    Column(
        modifier = modifier
            .astraCard(RoundedCornerShape(AppTheme.dimens.XS))
            .padding(
                vertical = AppTheme.dimens.XS,
                horizontal = AppTheme.dimens.S
            ),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS)
    ) {
        val scope = FilterCardScope.Default(this)
        content.invoke(scope)
    }
}

@Preview
@Composable
private fun FilterCardPreview() {
    AdaptThemeFade {
        FilterCard {
            TitleOption(text = "Filter")
            TextOption(text = "Tap an option to change its sort order")
        }
    }
}
