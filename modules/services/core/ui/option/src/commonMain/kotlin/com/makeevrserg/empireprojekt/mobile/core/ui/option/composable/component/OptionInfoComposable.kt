package com.makeevrserg.empireprojekt.mobile.core.ui.option.composable.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.makeevrserg.empireprojekt.mobile.core.ui.common.text.composable.AstraTextComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.theme.AppTheme

@Composable
fun OptionInfoComposable(
    text: String,
    endText: String,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    infoText: String? = null,
    iconTint: Color = AppTheme.astraColors.surface.onSecondary,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    OptionRowComposable(
        text = text,
        modifier = modifier.padding(contentPadding),
        textColor = AppTheme.astraColors.surface.onSecondary,
        icon = icon,
        iconTint = iconTint,
        infoText = infoText,
        end = {
            AstraTextComposable(
                text = endText,
                fontSize = 18.sp,
                textAlign = TextAlign.End,
                color = MaterialTheme.colors.onPrimary
            )
        }
    )
}

private const val PREVIEW_TEXT = "Some text"
private val PreviewLongText = List(size = 10) { PREVIEW_TEXT }.joinToString(separator = " ")

@Composable
private fun OptionInfoPreviewSheet(text: String, infoText: String?) {
    AdaptThemeFadeComposable {
        Column {
            OptionInfoComposable(
                icon = rememberVectorPainter(Icons.Filled.Image),
                text = text,
                infoText = infoText,
                endText = PREVIEW_TEXT,
                modifier = Modifier.padding(
                    horizontal = 8.dp,
                    vertical = 4.dp
                )
            )
            OptionSeparatorComposable(Modifier.fillMaxWidth())
        }
    }
}

@Preview
@Composable
private fun OptionInfoShortPreview() {
    OptionInfoPreviewSheet(text = PREVIEW_TEXT, infoText = null)
}

@Preview
@Composable
private fun OptionInfoLongPreview() {
    OptionInfoPreviewSheet(text = PreviewLongText, infoText = null)
}

@Preview
@Composable
private fun OptionInfoWithInfoPreview() {
    OptionInfoPreviewSheet(text = PREVIEW_TEXT, infoText = PREVIEW_TEXT)
}

@Preview
@Composable
private fun OptionInfoLongWithLongInfoPreview() {
    OptionInfoPreviewSheet(text = PreviewLongText, infoText = PreviewLongText)
}
