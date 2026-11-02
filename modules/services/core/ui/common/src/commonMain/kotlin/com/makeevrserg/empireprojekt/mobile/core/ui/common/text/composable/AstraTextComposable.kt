package com.makeevrserg.empireprojekt.mobile.core.ui.common.text.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.resources.jetbrainsmono_wght
import com.makeevrserg.empireprojekt.mobile.core.ui.common.util.asFontFamily
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable.AdaptThemeFadeComposable

@Composable
fun AstraTextComposable(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    style: TextStyle = LocalTextStyle.current,
    fontSize: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontFamily = MR.fonts.jetbrainsmono_wght.asFontFamily(),
        textAlign = textAlign,
        overflow = overflow,
        maxLines = maxLines,
        style = style
    )
}

@Preview
@Composable
private fun AstraTextPreview() {
    AdaptThemeFadeComposable {
        Column {
            AstraTextComposable(
                text = "Title",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onPrimary
            )
            AstraTextComposable(
                text = "Secondary text",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.secondaryVariant
            )
        }
    }
}
