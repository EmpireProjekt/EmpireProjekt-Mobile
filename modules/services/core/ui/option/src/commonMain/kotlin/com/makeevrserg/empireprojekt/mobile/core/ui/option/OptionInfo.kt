package com.makeevrserg.empireprojekt.mobile.core.ui.option

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
import com.makeevrserg.empireprojekt.mobile.core.ui.text.AstraText
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

private val END_TEXT_FONT_SIZE = 18.sp

@Composable
fun OptionInfo(
    text: String,
    endText: String,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    infoText: String? = null,
    iconTint: Color = AppTheme.astraColors.surface.onSecondary,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    OptionRow(
        text = text,
        modifier = modifier.padding(contentPadding),
        textColor = AppTheme.astraColors.surface.onSecondary,
        icon = icon,
        iconTint = iconTint,
        infoText = infoText,
        end = {
            AstraText(
                text = endText,
                fontSize = END_TEXT_FONT_SIZE,
                textAlign = TextAlign.End,
                color = MaterialTheme.colors.onPrimary
            )
        }
    )
}

@Composable
@Preview
private fun OptionInfoPreview() {
    AdaptThemeFade {
        val modifier = Modifier.padding(
            horizontal = 8.dp,
            vertical = 4.dp
        )
        Column {
            OptionInfo(
                icon = rememberVectorPainter(Icons.Filled.Image),
                text = TEXT,
                endText = TEXT,
                modifier = modifier
            )
            OptionSeparator(Modifier.fillMaxWidth())
            OptionInfo(
                icon = rememberVectorPainter(Icons.Filled.Image),
                text = LONG_TEXT,
                endText = TEXT,
                modifier = modifier
            )
            OptionSeparator(Modifier.fillMaxWidth())
            OptionInfo(
                icon = rememberVectorPainter(Icons.Filled.Image),
                text = TEXT,
                infoText = TEXT,
                endText = TEXT,
                modifier = modifier
            )
            OptionSeparator(Modifier.fillMaxWidth())
            OptionInfo(
                icon = rememberVectorPainter(Icons.Filled.Image),
                text = LONG_TEXT,
                infoText = TEXT,
                endText = TEXT,
                modifier = modifier
            )
            OptionSeparator(Modifier.fillMaxWidth())
            OptionInfo(
                icon = rememberVectorPainter(Icons.Filled.Image),
                text = LONG_TEXT,
                infoText = LONG_TEXT,
                endText = TEXT,
                modifier = modifier
            )
            OptionSeparator(Modifier.fillMaxWidth())
            OptionInfo(
                icon = rememberVectorPainter(Icons.Filled.Image),
                text = TEXT,
                infoText = LONG_TEXT,
                endText = TEXT,
                modifier = modifier
            )
        }
    }
}
