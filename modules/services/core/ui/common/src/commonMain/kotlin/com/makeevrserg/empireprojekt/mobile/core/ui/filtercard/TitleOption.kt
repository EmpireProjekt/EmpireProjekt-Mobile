package com.makeevrserg.empireprojekt.mobile.core.ui.filtercard

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asFontFamily

@Composable
fun TitleOption(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colors.onPrimary,
        textAlign = TextAlign.Start,
        modifier = Modifier,
        style = MaterialTheme.typography.h6,
        fontFamily = MR.fonts.jetbrainsmono_wght.asFontFamily()
    )
}
