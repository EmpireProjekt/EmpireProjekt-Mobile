package com.makeevrserg.empireprojekt.mobile.core.ui.placeholder.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

private const val SEGMENT_COUNT = 8
private const val GRID_SIDE = 3
private const val VISIBILITY_STEP_COUNT = 5f
private val BaseFrameDelay = 80.milliseconds
private val FrameDelayStep = 4.milliseconds

@Composable
private fun AstraLoadingSegment(
    phase: Int,
    index: Int,
    size: Dp,
    color: Color
) {
    val visibility = index.minus(phase).mod(SEGMENT_COUNT).div(VISIBILITY_STEP_COUNT)
    Box(
        Modifier
            .size(size)
            .padding(2.dp)
            .scale(visibility.coerceIn(minimumValue = 0f, maximumValue = 1f))
            .background(
                color = color,
                shape = RoundedCornerShape(2.dp)
            )
    )
}

@Composable
private fun AstraLoadingTopRow(phase: Int, size: Dp) {
    val logo = AppTheme.astraColors.astraLogo
    Row(Modifier.fillMaxWidth()) {
        AstraLoadingSegment(
            phase = phase,
            index = 7,
            size = size,
            color = logo.astraRed
        )
        AstraLoadingSegment(
            phase = phase,
            index = 0,
            size = size,
            color = logo.astraBlue
        )
        AstraLoadingSegment(
            phase = phase,
            index = 1,
            size = size,
            color = logo.astraOrange
        )
    }
}

@Composable
private fun AstraLoadingMiddleRow(phase: Int, size: Dp) {
    val logo = AppTheme.astraColors.astraLogo
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        AstraLoadingSegment(
            phase = phase,
            index = 6,
            size = size,
            color = logo.astraRed
        )
        AstraLoadingSegment(
            phase = phase,
            index = 2,
            size = size,
            color = logo.astraYellow
        )
    }
}

@Composable
private fun AstraLoadingBottomRow(phase: Int, size: Dp) {
    val logo = AppTheme.astraColors.astraLogo
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        AstraLoadingSegment(
            phase = phase,
            index = 5,
            size = size,
            color = logo.astraRed
        )
        AstraLoadingSegment(
            phase = phase,
            index = 4,
            size = size,
            color = logo.astraRed
        )
        AstraLoadingSegment(
            phase = phase,
            index = 3,
            size = size,
            color = logo.astraRed
        )
    }
}

@Composable
fun AstraLoading(
    modifier: Modifier = Modifier,
    size: Dp = 64.dp
) {
    var position by remember { mutableIntStateOf(1) }
    LaunchedEffect(Unit) {
        while (true) {
            val phase = position.mod(SEGMENT_COUNT)
            delay(BaseFrameDelay - FrameDelayStep * phase)
            position += 1
        }
    }
    val phase = position.mod(SEGMENT_COUNT)
    Column(
        modifier = modifier.width(size * GRID_SIDE),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AstraLoadingTopRow(phase = phase, size = size)
        AstraLoadingMiddleRow(phase = phase, size = size)
        AstraLoadingBottomRow(phase = phase, size = size)
    }
}

@Preview
@Composable
private fun AstraLoadingPreview() {
    AdaptThemeFade {
        AstraLoading()
    }
}
