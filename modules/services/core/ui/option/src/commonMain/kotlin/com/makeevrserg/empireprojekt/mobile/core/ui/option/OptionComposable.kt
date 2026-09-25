package com.makeevrserg.empireprojekt.mobile.core.ui.option

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Composable
fun OptionComposable(
    text: String,
    end: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    iconTint: Color = AppTheme.astraColors.surface.onSecondary,
    infoText: String? = null,
    onClick: (() -> Unit)? = null,
) {
    OptionRow(
        text = text,
        modifier = Modifier
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .then(modifier),
        icon = icon,
        iconTint = iconTint,
        infoText = infoText,
        end = { end.invoke() }
    )
}

@Composable
@Preview
@Suppress("LongMethod")
private fun OptionComposablePreview() {
    val modifier = Modifier.padding(
        horizontal = 8.dp,
        vertical = 4.dp
    )
    Column {
        OptionComposable(
            icon = rememberVectorPainter(Icons.Filled.Image),
            text = TEXT,
            onClick = {},
            modifier = modifier,
            end = {
                Text("Hello!")
            }
        )
        OptionSeparator(Modifier.fillMaxWidth())
        OptionComposable(
            icon = rememberVectorPainter(Icons.Filled.Image),
            text = LONG_TEXT,
            onClick = {},
            modifier = modifier,
            end = {
                Text("Hello!")
            }
        )
        OptionSeparator(Modifier.fillMaxWidth())
        OptionComposable(
            icon = rememberVectorPainter(Icons.Filled.Image),
            text = TEXT,
            infoText = TEXT,
            onClick = {},
            modifier = modifier,
            end = {
                Text("Hello!")
            }
        )
        OptionSeparator(Modifier.fillMaxWidth())
        OptionComposable(
            icon = rememberVectorPainter(Icons.Filled.Image),
            text = LONG_TEXT,
            infoText = TEXT,
            onClick = {},
            modifier = modifier,
            end = {
                Text("Hello!")
            }
        )
        OptionSeparator(Modifier.fillMaxWidth())
        OptionComposable(
            icon = rememberVectorPainter(Icons.Filled.Image),
            text = LONG_TEXT,
            infoText = LONG_TEXT,
            onClick = {},
            modifier = modifier,
            end = {
                Text("Hello!")
            }
        )
        OptionSeparator(Modifier.fillMaxWidth())
        OptionComposable(
            icon = rememberVectorPainter(Icons.Filled.Image),
            text = TEXT,
            infoText = LONG_TEXT,
            onClick = {},
            modifier = modifier,
            end = {
                Text("Hello!")
            }
        )
    }
}
