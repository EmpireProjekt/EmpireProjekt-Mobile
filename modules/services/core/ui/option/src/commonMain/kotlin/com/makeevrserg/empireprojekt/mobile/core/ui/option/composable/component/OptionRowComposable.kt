package com.makeevrserg.empireprojekt.mobile.core.ui.option.composable.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.makeevrserg.empireprojekt.mobile.core.ui.common.text.composable.AstraTextComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.theme.AppTheme

@Composable
internal fun OptionRowComposable(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colors.onPrimary,
    icon: Painter? = null,
    iconTint: Color = AppTheme.astraColors.surface.onSecondary,
    infoText: String? = null,
    infoSpacing: Dp = 0.dp,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    end: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS, Alignment.CenterHorizontally),
        verticalAlignment = verticalAlignment
    ) {
        if (icon != null) {
            Icon(
                painter = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(24.dp)
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            AstraTextComposable(
                text = text,
                color = textColor,
                textAlign = TextAlign.Start,
                fontSize = 18.sp
            )
            if (infoText != null) {
                if (infoSpacing > 0.dp) {
                    Spacer(Modifier.height(infoSpacing))
                }
                AstraTextComposable(
                    text = infoText,
                    color = AppTheme.astraColors.surface.onSecondary,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Start
                )
            }
        }
        end.invoke(this)
    }
}

private const val PREVIEW_TEXT = "Some text"
private val PreviewLongText = List(size = 10) { PREVIEW_TEXT }.joinToString(separator = " ")

@Preview
@Composable
private fun OptionRowPreview() {
    AdaptThemeFadeComposable {
        OptionRowComposable(
            text = PREVIEW_TEXT,
            icon = rememberVectorPainter(Icons.Filled.Image),
            end = { AstraTextComposable(text = "20m") }
        )
    }
}

@Preview
@Composable
private fun OptionRowWithInfoPreview() {
    AdaptThemeFadeComposable {
        OptionRowComposable(
            text = PREVIEW_TEXT,
            icon = rememberVectorPainter(Icons.Filled.Image),
            infoText = PreviewLongText,
            end = { AstraTextComposable(text = "20m") }
        )
    }
}
