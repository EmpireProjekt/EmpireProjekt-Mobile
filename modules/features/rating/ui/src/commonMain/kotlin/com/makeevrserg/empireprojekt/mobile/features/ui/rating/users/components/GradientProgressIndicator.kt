package com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Composable
internal fun GradientProgressIndicator(
    brush: Brush,
    progress: Float,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primaryVariant
) {
    Box(modifier.fillMaxWidth()) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(AppTheme.dimens.XXS)
        ) {
            drawRect(
                color = backgroundColor,
                topLeft = Offset(x = 0f, y = 0f),
                size = Size(size.width, size.height)
            )
            drawRect(
                brush = brush,
                topLeft = Offset(x = 0f, y = 0f),
                size = Size(progress * size.width, size.height)
            )
        }
    }
}

@Preview
@Composable
private fun GradientProgressIndicatorPreview() {
    AdaptThemeFade {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.primaryVariant)
                .padding(AppTheme.dimens.S)
        ) {
            GradientProgressIndicator(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color.Red, Color.Yellow, Color.Green)
                ),
                progress = 0.6f
            )
        }
    }
}
