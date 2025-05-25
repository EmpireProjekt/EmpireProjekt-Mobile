package com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.common.placeholder
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Composable
fun RatingUserShimmerWidget() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AppTheme.dimens.S))
            .background(MaterialTheme.colors.primary)
            .padding(AppTheme.dimens.S),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS)
    ) {
        Box(
            Modifier
                .height(AppTheme.dimens.S)
                .fillMaxWidth(0.4f)
                .placeholder(true)
        )
        Spacer(Modifier.height(AppTheme.dimens.S))
        Box(
            Modifier
                .height(AppTheme.dimens.S)
                .fillMaxWidth(0.6f)
                .placeholder(true)
        )
        Box(
            Modifier
                .height(AppTheme.dimens.S)
                .fillMaxWidth(0.3f)
                .placeholder(true)
        )
    }
}

@Preview
@Composable
private fun RatingUserShimmerWidgetPreview() {
    AdaptThemeFade {
        RatingUserShimmerWidget()
    }
}