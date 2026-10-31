package com.makeevrserg.empireprojekt.mobile.features.ui.status.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Token
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.status.api.url.component.UrlStatusComponent

@Composable
internal fun IconWidgetComposable(status: UrlStatusComponent.LoadingStatus) {
    val icon = when (status) {
        UrlStatusComponent.LoadingStatus.LOADING -> Icons.Filled.Token
        UrlStatusComponent.LoadingStatus.SUCCESS -> Icons.Filled.Bolt
        UrlStatusComponent.LoadingStatus.ERROR -> Icons.Filled.Error
    }
    val tint = when (status) {
        UrlStatusComponent.LoadingStatus.LOADING -> AppTheme.astraColors.astraLogo.astraYellow
        UrlStatusComponent.LoadingStatus.SUCCESS -> AppTheme.astraColors.action.colorPositive
        UrlStatusComponent.LoadingStatus.ERROR -> AppTheme.astraColors.action.colorNegative
    }
    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = tint,
        modifier = Modifier.padding(horizontal = AppTheme.dimens.S)
    )
}

@Preview
@Composable
private fun IconWidgetPreview() {
    AdaptThemeFadeComposable {
        Row(
            modifier = Modifier.background(MaterialTheme.colors.primary),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS)
        ) {
            UrlStatusComponent.LoadingStatus.entries.forEach { status ->
                IconWidgetComposable(status = status)
            }
        }
    }
}
