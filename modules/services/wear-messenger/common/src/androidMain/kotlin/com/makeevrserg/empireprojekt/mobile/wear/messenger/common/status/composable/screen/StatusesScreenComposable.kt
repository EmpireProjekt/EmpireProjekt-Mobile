package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.status.composable.screen

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
import com.makeevrserg.empireprojekt.mobile.core.ui.common.util.asPainter
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.status.model.WearStatusState
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.composable.component.IconTextChipComposable
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.util.formatClockTime
import kotlin.time.Instant

@Composable
internal fun StatusesScreenComposable(
    model: WearStatusState,
    modifier: Modifier = Modifier
) {
    val listState = rememberScalingLazyListState()
    Scaffold(
        modifier = modifier.background(MaterialTheme.colors.primaryVariant),
        positionIndicator = {
            PositionIndicator(scalingLazyListState = listState)
        }
    ) {
        ScalingLazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            autoCentering = AutoCenteringParams(itemIndex = 0)
        ) {
            item {
                Text(
                    text = model.updatedAt.formatClockTime(),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            item {
                IconTextChipComposable(
                    modifier = Modifier.fillMaxWidth(),
                    text = model.successCount.toString(),
                    painter = MR.images.ic_wifi_tethering.asPainter(),
                    iconColor = AppTheme.astraColors.action.colorPositive
                )
            }
            item {
                IconTextChipComposable(
                    modifier = Modifier.fillMaxWidth(),
                    text = model.loadingCount.toString(),
                    painter = MR.images.ic_wifi_tethering_error.asPainter(),
                    iconColor = AppTheme.astraColors.astraLogo.astraOrange
                )
            }
            item {
                IconTextChipComposable(
                    modifier = Modifier.fillMaxWidth(),
                    text = model.failureCount.toString(),
                    painter = MR.images.ic_wifi_tethering_off.asPainter(),
                    iconColor = AppTheme.astraColors.action.colorNegative
                )
            }
        }
    }
}

private val PreviewUpdatedAt = Instant.fromEpochMilliseconds(1_700_000_000_000)

@Preview(device = "id:wearos_large_round")
@Composable
private fun StatusesScreenEmptyPreview() {
    AdaptThemeFadeComposable {
        StatusesScreenComposable(
            model = WearStatusState(
                loadingCount = 0,
                successCount = 0,
                failureCount = 0,
                updatedAt = null
            )
        )
    }
}

@Preview(device = "id:wearos_large_round")
@Composable
private fun StatusesScreenFilledPreview() {
    AdaptThemeFadeComposable {
        StatusesScreenComposable(
            model = WearStatusState(
                loadingCount = 1,
                successCount = 3,
                failureCount = 2,
                updatedAt = PreviewUpdatedAt
            )
        )
    }
}
