package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.composable.component

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.resources.img_splash
import com.makeevrserg.empireprojekt.mobile.core.ui.common.util.asPainter
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable.AdaptThemeFadeComposable

@Composable
internal fun IconTextChipComposable(
    text: String,
    painter: Painter,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colors.onPrimary,
    iconColor: Color = Color.Unspecified,
    onClick: () -> Unit = { }
) {
    AstraChipComposable(
        modifier = modifier,
        label = {
            Crossfade(
                targetState = text,
                label = "LABEL"
            ) { currentText ->
                Text(
                    text = currentText,
                    style = MaterialTheme.typography.caption,
                    color = textColor
                )
            }
        },
        onClick = onClick,
        icon = {
            Crossfade(
                targetState = painter,
                label = "LABEL"
            ) { currentPainter ->
                Icon(
                    painter = currentPainter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(ChipDefaults.LargeIconSize)
                        .wrapContentSize(align = Alignment.Center),
                    tint = iconColor
                )
            }
        }
    )
}

@Preview
@Composable
private fun IconTextChipPreview() {
    AdaptThemeFadeComposable {
        IconTextChipComposable(
            text = "EmpireProjekt",
            painter = MR.images.img_splash.asPainter()
        )
    }
}
