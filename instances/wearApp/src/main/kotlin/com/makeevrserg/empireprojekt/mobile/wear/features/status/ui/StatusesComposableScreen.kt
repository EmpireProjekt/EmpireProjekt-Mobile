package com.makeevrserg.empireprojekt.mobile.wear.features.status.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_wifi_tethering
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_wifi_tethering_error
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_wifi_tethering_off
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asPainter
import com.makeevrserg.empireprojekt.mobile.wear.features.components.IconTextChip
import com.makeevrserg.empireprojekt.mobile.wear.features.status.presentation.WearStatusComponent

@Composable
internal fun StatusesComposableScreen(
    model: WearStatusComponent.Model,
    modifier: Modifier = Modifier
) {
    val listState = rememberScalingLazyListState()
    Scaffold(
        modifier = modifier.background(MaterialTheme.colors.primaryVariant),
        positionIndicator = {
            PositionIndicator(
                scalingLazyListState = listState
            )
        }
    ) {
        ScalingLazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            autoCentering = AutoCenteringParams(itemIndex = 0),
        ) {
            item {
                Text(
                    text = model.updatedAt,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            item {
                IconTextChip(
                    modifier = Modifier.fillMaxWidth(),
                    text = model.successCount.toString(),
                    painter = MR.images.ic_wifi_tethering.asPainter(),
                    iconColor = AppTheme.astraColors.action.colorPositive
                )
            }
            item {
                IconTextChip(
                    modifier = Modifier.fillMaxWidth(),
                    text = model.loadingCount.toString(),
                    painter = MR.images.ic_wifi_tethering_error.asPainter(),
                    iconColor = AppTheme.astraColors.astraLogo.astraOrange
                )
            }
            item {
                IconTextChip(
                    modifier = Modifier.fillMaxWidth(),
                    text = model.failureCount.toString(),
                    painter = MR.images.ic_wifi_tethering_off.asPainter(),
                    iconColor = AppTheme.astraColors.action.colorNegative
                )
            }
        }
    }
}

@Preview(device = "id:wearos_large_round")
@Composable
private fun StatusesComposableScreenEmptyPreview() {
    AdaptThemeFade {
        StatusesComposableScreen(model = WearStatusComponent.Model())
    }
}

@Preview(device = "id:wearos_large_round")
@Composable
private fun StatusesComposableScreenFilledPreview() {
    AdaptThemeFade {
        StatusesComposableScreen(
            model = WearStatusComponent.Model(
                loadingCount = 1,
                successCount = 3,
                failureCount = 2,
                updatedAt = "20:44:00"
            )
        )
    }
}
