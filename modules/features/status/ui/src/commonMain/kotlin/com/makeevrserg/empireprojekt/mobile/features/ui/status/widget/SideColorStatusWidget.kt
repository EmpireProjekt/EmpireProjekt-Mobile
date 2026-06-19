package com.makeevrserg.empireprojekt.mobile.features.ui.status.widget

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
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.status.url.presentation.UrlStatusComponent

@Composable
internal fun SideColorStatusWidget(
    status: UrlStatusComponent.LoadingStatus,
    isLoading: Boolean
) {
    when {
        status == UrlStatusComponent.LoadingStatus.LOADING || isLoading -> {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(AppTheme.dimens.XXS),
                color = AppTheme.astraColors.astraLogo.astraOrange,
                backgroundColor = AppTheme.astraColors.astraLogo.astraYellow
            )
        }

        status == UrlStatusComponent.LoadingStatus.SUCCESS -> {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(AppTheme.dimens.XXS)
                    .background(AppTheme.astraColors.action.colorPositive)
            )
        }

        status == UrlStatusComponent.LoadingStatus.ERROR -> {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(AppTheme.dimens.XXS)
                    .background(AppTheme.astraColors.action.colorNegative)
            )
        }
    }
}

@Preview
@Composable
private fun SideColorStatusWidgetPreview() {
    AdaptThemeFade {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .height(AppTheme.dimens.XL),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.S)
        ) {
            UrlStatusComponent.LoadingStatus.entries.forEach { status ->
                SideColorStatusWidget(status = status, isLoading = false)
            }
        }
    }
}
