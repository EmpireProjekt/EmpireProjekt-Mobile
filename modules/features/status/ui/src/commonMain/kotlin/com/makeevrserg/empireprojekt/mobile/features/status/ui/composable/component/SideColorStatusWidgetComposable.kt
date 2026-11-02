package com.makeevrserg.empireprojekt.mobile.features.status.ui.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.status.api.url.model.UrlStatusState

@Composable
private fun SideLoadingBarComposable() {
    LinearProgressIndicator(
        modifier = Modifier
            .fillMaxHeight()
            .width(AppTheme.dimens.XXS),
        color = AppTheme.astraColors.astraLogo.astraOrange,
        backgroundColor = AppTheme.astraColors.astraLogo.astraYellow
    )
}

@Composable
private fun SideColorBarComposable(color: Color) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(AppTheme.dimens.XXS)
            .background(color)
    )
}

@Composable
internal fun SideColorStatusWidgetComposable(
    status: UrlStatusState.LoadingStatus,
    isLoading: Boolean
) {
    val positiveColor = AppTheme.astraColors.action.colorPositive
    val negativeColor = AppTheme.astraColors.action.colorNegative
    when {
        status == UrlStatusState.LoadingStatus.LOADING || isLoading -> SideLoadingBarComposable()
        status == UrlStatusState.LoadingStatus.SUCCESS -> SideColorBarComposable(positiveColor)
        status == UrlStatusState.LoadingStatus.ERROR -> SideColorBarComposable(negativeColor)
    }
}

@Preview
@Composable
private fun SideColorStatusWidgetPreview() {
    AdaptThemeFadeComposable {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .height(AppTheme.dimens.XL),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.S)
        ) {
            UrlStatusState.LoadingStatus.entries.forEach { status ->
                SideColorStatusWidgetComposable(status = status, isLoading = false)
            }
        }
    }
}
