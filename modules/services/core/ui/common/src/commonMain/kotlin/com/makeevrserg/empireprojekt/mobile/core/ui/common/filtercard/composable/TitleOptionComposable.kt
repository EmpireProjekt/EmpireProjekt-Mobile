package com.makeevrserg.empireprojekt.mobile.core.ui.common.filtercard.composable

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.common.text.composable.AstraTextComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable.AdaptThemeFadeComposable

@Composable
fun TitleOptionComposable(
    text: String,
    modifier: Modifier = Modifier
) {
    AstraTextComposable(
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
    AdaptThemeFadeComposable {
        TitleOptionComposable(text = "Filter")
    }
}
