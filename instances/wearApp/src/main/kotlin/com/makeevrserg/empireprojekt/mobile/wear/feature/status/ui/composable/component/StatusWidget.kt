package com.makeevrserg.empireprojekt.mobile.wear.feature.status.ui.composable.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_wifi_tethering
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_wifi_tethering_error
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_wifi_tethering_off
import com.makeevrserg.empireprojekt.mobile.core.ui.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asPainter
import com.makeevrserg.empireprojekt.mobile.features.status.api.url.component.UrlStatusComponent
import com.makeevrserg.empireprojekt.mobile.wear.feature.composable.component.IconTextChip

@Composable
internal fun StatusWidgetContent(
    status: UrlStatusComponent.LoadingStatus,
    onClick: () -> Unit
) {
    val icon = when (status) {
        UrlStatusComponent.LoadingStatus.LOADING -> MR.images.ic_wifi_tethering_error
        UrlStatusComponent.LoadingStatus.SUCCESS -> MR.images.ic_wifi_tethering
        UrlStatusComponent.LoadingStatus.ERROR -> MR.images.ic_wifi_tethering_off
    }.asPainter()
    val color by animateColorAsState(
        targetValue = when (status) {
            UrlStatusComponent.LoadingStatus.LOADING -> AppTheme.astraColors.astraLogo.astraOrange
            UrlStatusComponent.LoadingStatus.SUCCESS -> AppTheme.astraColors.action.colorPositive
            UrlStatusComponent.LoadingStatus.ERROR -> AppTheme.astraColors.action.colorNegative
        },
        label = "status icon tint"
    )
    IconTextChip(
        text = "EmpireProjekt.ru",
        painter = icon,
        iconColor = color,
        onClick = onClick
    )
}

@Composable
internal fun StatusWidget(component: UrlStatusComponent) {
    val model by component.model.collectAsState()
    StatusWidgetContent(
        status = model.status,
        onClick = component::checkStatus
    )
}

@Preview
@Composable
private fun StatusWidgetContentLoadingPreview() {
    AdaptThemeFadeComposable {
        StatusWidgetContent(status = UrlStatusComponent.LoadingStatus.LOADING, onClick = {})
    }
}

@Preview
@Composable
private fun StatusWidgetContentSuccessPreview() {
    AdaptThemeFadeComposable {
        StatusWidgetContent(status = UrlStatusComponent.LoadingStatus.SUCCESS, onClick = {})
    }
}

@Preview
@Composable
private fun StatusWidgetContentErrorPreview() {
    AdaptThemeFadeComposable {
        StatusWidgetContent(status = UrlStatusComponent.LoadingStatus.ERROR, onClick = {})
    }
}
