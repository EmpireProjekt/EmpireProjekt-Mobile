package com.makeevrserg.empireprojekt.mobile.core.ui.option

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

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
    Row(
        modifier = modifier
            .padding(contentPadding),
        horizontalArrangement = Arrangement.spacedBy(
            8.dp,
            Alignment.End
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.let { icon ->
            Icon(
                painter = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(28.dp)
            )
        }
        Column(
            modifier = Modifier.weight(1f, true),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                color = AppTheme.astraColors.surface.onSecondary,
                textAlign = TextAlign.Start,
                fontSize = 18.sp
            )
            infoText?.let {
                Text(
                    text = infoText,
                    color = AppTheme.astraColors.surface.onSecondary,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Start,
                )
            }
        }
        Text(
            text = endText,
            fontSize = 18.sp,
            textAlign = TextAlign.End,
            color = MaterialTheme.colors.onPrimary,
        )
    }
}

@Composable
@Preview
private fun OptionInfoPreview() {
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
