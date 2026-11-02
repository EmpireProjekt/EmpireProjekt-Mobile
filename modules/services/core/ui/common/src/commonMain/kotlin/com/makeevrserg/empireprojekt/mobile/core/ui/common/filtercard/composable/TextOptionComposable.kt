package com.makeevrserg.empireprojekt.mobile.core.ui.common.filtercard.composable

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.common.text.composable.AstraTextComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable.AdaptThemeFadeComposable

@Composable
fun TextOptionComposable(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.secondaryVariant
) {
    AstraTextComposable(
        text = text,
        color = color,
        textAlign = TextAlign.Start,
        modifier = modifier,
        style = MaterialTheme.typography.subtitle2
    )
}

@Preview
@Composable
private fun TextOptionPreview() {
    AdaptThemeFadeComposable {
        TextOptionComposable(text = "Tap an option to change its sort order")
    }
}
