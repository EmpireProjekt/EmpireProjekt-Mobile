package com.makeevrserg.empireprojekt.mobile.features.status.ui.composable.component

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
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.status.api.url.model.UrlStatusState

@Composable
internal fun IconWidgetComposable(status: UrlStatusState.LoadingStatus) {
    val icon = when (status) {
        UrlStatusState.LoadingStatus.LOADING -> Icons.Filled.Token
        UrlStatusState.LoadingStatus.SUCCESS -> Icons.Filled.Bolt
        UrlStatusState.LoadingStatus.ERROR -> Icons.Filled.Error
    }
    val tint = when (status) {
        UrlStatusState.LoadingStatus.LOADING -> AppTheme.astraColors.astraLogo.astraYellow
        UrlStatusState.LoadingStatus.SUCCESS -> AppTheme.astraColors.action.colorPositive
        UrlStatusState.LoadingStatus.ERROR -> AppTheme.astraColors.action.colorNegative
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
            UrlStatusState.LoadingStatus.entries.forEach { status ->
                IconWidgetComposable(status = status)
            }
        }
    }
}
