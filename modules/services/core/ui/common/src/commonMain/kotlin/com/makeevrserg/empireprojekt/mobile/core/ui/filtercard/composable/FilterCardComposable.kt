package com.makeevrserg.empireprojekt.mobile.core.ui.filtercard.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.filtercard.model.FilterCardScope
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.util.astraCard

@Composable
fun FilterCardComposable(
    modifier: Modifier = Modifier,
    content: @Composable FilterCardScope.() -> Unit
) {
    Column(
        modifier = modifier
            .astraCard(
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(AppTheme.dimens.XS)
            )
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
    AdaptThemeFadeComposable {
        FilterCardComposable {
            TitleOptionComposable(text = "Filter")
            TextOptionComposable(text = "Tap an option to change its sort order")
        }
    }
}
