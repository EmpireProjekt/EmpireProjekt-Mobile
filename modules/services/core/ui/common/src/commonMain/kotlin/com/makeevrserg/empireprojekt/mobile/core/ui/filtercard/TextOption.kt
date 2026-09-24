package com.makeevrserg.empireprojekt.mobile.core.ui.filtercard

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.makeevrserg.empireprojekt.mobile.core.ui.text.AstraText

@Composable
fun TextOption(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.secondaryVariant
) {
    AstraText(
        text = text,
        color = color,
        textAlign = TextAlign.Start,
        modifier = modifier,
        style = MaterialTheme.typography.subtitle2
    )
}
