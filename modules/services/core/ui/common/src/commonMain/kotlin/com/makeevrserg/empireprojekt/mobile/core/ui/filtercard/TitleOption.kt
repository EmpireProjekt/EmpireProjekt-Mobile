package com.makeevrserg.empireprojekt.mobile.core.ui.filtercard

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.text.AstraText
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade

@Composable
fun TitleOption(
    text: String,
    modifier: Modifier = Modifier
) {
    AstraText(
        text = text,
        color = MaterialTheme.colors.onPrimary,
        textAlign = TextAlign.Start,
        modifier = modifier,
        style = MaterialTheme.typography.h6
    )
}

@Preview
@Composable
private fun TitleOptionPreview() {
    AdaptThemeFade {
        TitleOption(text = "Filter")
    }
}
