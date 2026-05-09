package com.makeevrserg.empireprojekt.mobile.wear.features.status.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asPainter
import com.makeevrserg.empireprojekt.mobile.wear.features.components.IconTextChip
import com.makeevrserg.empireprojekt.mobile.wear.features.status.presentation.WearStatusComponent

@Composable
fun StatusesScreen(
    wearStatusComponent: WearStatusComponent,
    modifier: Modifier = Modifier
) {
    val mergedState by wearStatusComponent.mergedState.collectAsState()
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
                    text = mergedState.updatedAt,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            item {
                IconTextChip(
                    modifier = Modifier.fillMaxWidth(),
                    text = mergedState.successCount.toString(),
                    painter = MR.images.ic_wifi_tethering.asPainter(),
                    iconColor = AppTheme.astraColors.action.colorPositive
                )
            }
            item {
                IconTextChip(
                    modifier = Modifier.fillMaxWidth(),
                    text = mergedState.loadingCount.toString(),
                    painter = MR.images.ic_wifi_tethering_error.asPainter(),
                    iconColor = AppTheme.astraColors.astraLogo.astraOrange
                )
            }
            item {
                IconTextChip(
                    modifier = Modifier.fillMaxWidth(),
                    text = mergedState.failureCount.toString(),
                    painter = MR.images.ic_wifi_tethering_off.asPainter(),
                    iconColor = AppTheme.astraColors.action.colorNegative
                )
            }
        }
    }
}
