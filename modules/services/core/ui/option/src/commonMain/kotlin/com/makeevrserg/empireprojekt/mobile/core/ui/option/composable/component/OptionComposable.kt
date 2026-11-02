package com.makeevrserg.empireprojekt.mobile.core.ui.option.composable.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.theme.AppTheme

@Composable
fun OptionComposable(
    text: String,
    end: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    iconTint: Color = AppTheme.astraColors.surface.onSecondary,
    infoText: String? = null,
    onClick: (() -> Unit)? = null,
) {
    OptionRowComposable(
        text = text,
        modifier = Modifier
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .then(modifier),
        icon = icon,
        iconTint = iconTint,
        infoText = infoText,
        end = end
    )
}

private const val PREVIEW_TEXT = "Some text"
private val PreviewLongText = List(size = 10) { PREVIEW_TEXT }.joinToString(separator = " ")

@Composable
private fun OptionComposablePreviewSheet(text: String, infoText: String?) {
    AdaptThemeFadeComposable {
        Column {
            OptionComposable(
                icon = rememberVectorPainter(Icons.Filled.Image),
                text = text,
                infoText = infoText,
                onClick = {},
                modifier = Modifier.padding(
                    horizontal = 8.dp,
                    vertical = 4.dp
                ),
                end = { Text("Hello!") }
            )
            OptionSeparatorComposable(Modifier.fillMaxWidth())
        }
    }
}

@Preview
@Composable
private fun OptionComposableShortPreview() {
    OptionComposablePreviewSheet(text = PREVIEW_TEXT, infoText = null)
}

@Preview
@Composable
private fun OptionComposableLongPreview() {
    OptionComposablePreviewSheet(text = PreviewLongText, infoText = null)
}

@Preview
@Composable
private fun OptionComposableWithInfoPreview() {
    OptionComposablePreviewSheet(text = PREVIEW_TEXT, infoText = PREVIEW_TEXT)
}

@Preview
@Composable
private fun OptionComposableLongWithLongInfoPreview() {
    OptionComposablePreviewSheet(text = PreviewLongText, infoText = PreviewLongText)
}
