package com.makeevrserg.empireprojekt.mobile.core.ui.filtercard

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asFontFamily

@Composable
fun TextOption(
    text: String,
    color: Color = MaterialTheme.colors.secondaryVariant
) {
    Text(
        text = text,
        color = color,
        textAlign = TextAlign.Start,
        modifier = Modifier,
        style = MaterialTheme.typography.subtitle2,
        fontFamily = MR.fonts.jetbrainsmono_wght.asFontFamily()
    )
}
