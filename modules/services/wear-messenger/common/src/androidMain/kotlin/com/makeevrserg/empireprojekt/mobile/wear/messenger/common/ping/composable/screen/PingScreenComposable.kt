package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.ping.composable.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_network_check
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_network_wifi
import com.makeevrserg.empireprojekt.mobile.core.ui.common.util.asPainter
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.ping.component.PingComponent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.util.formatClockTime
import kotlin.time.Instant

@Composable
private fun PingContentComposable(model: PingComponent.Model) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (model) {
                is PingComponent.Model.NoConnection -> {
                    Icon(
                        painter = MR.images.ic_network_check.asPainter(),
                        contentDescription = null,
                        tint = MaterialTheme.colors.error
                    )
                    Text(
                        text = model.updatedAt.formatClockTime(),
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                is PingComponent.Model.Success -> {
                    Icon(
                        painter = MR.images.ic_network_wifi.asPainter(),
                        contentDescription = null,
                        tint = MaterialTheme.colors.secondaryVariant
                    )
                    Text(
                        text = model.updatedAt.formatClockTime(),
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
internal fun PingScreenComposable(
    model: PingComponent.Model,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.background(MaterialTheme.colors.primaryVariant)
    ) {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            autoCentering = AutoCenteringParams(itemIndex = 0)
        ) {
            item {
                PingContentComposable(model = model)
            }
        }
    }
}

private val PreviewUpdatedAt = Instant.fromEpochMilliseconds(1_700_000_000_000)

@Composable
@Preview(device = "id:wearos_large_round")
private fun PingContentSuccessPreview() {
    AdaptThemeFadeComposable {
        PingContentComposable(model = PingComponent.Model.Success(updatedAt = PreviewUpdatedAt))
    }
}

@Composable
@Preview(device = "id:wearos_large_round")
private fun PingContentNoConnectionPreview() {
    AdaptThemeFadeComposable {
        PingContentComposable(model = PingComponent.Model.NoConnection(updatedAt = PreviewUpdatedAt))
    }
}

@Composable
@Preview(device = "id:wearos_large_round")
private fun PingScreenSuccessPreview() {
    AdaptThemeFadeComposable {
        PingScreenComposable(model = PingComponent.Model.Success(updatedAt = PreviewUpdatedAt))
    }
}

@Composable
@Preview(device = "id:wearos_large_round")
private fun PingScreenNoConnectionPreview() {
    AdaptThemeFadeComposable {
        PingScreenComposable(model = PingComponent.Model.NoConnection(updatedAt = PreviewUpdatedAt))
    }
}
