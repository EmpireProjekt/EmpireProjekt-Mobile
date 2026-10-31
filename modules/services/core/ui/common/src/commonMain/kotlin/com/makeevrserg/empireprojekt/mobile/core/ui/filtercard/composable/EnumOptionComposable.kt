package com.makeevrserg.empireprojekt.mobile.core.ui.filtercard.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import com.makeevrserg.empireprojekt.mobile.core.ui.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.text.composable.AstraTextComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Composable
fun <E : Enum<E>> EnumOptionComposable(
    text: String,
    selected: E?,
    toString: @Composable (E) -> String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(AppTheme.dimens.XS))
            .clickable { onClick.invoke() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AstraTextComposable(
            text = text,
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Start,
            modifier = Modifier,
            style = MaterialTheme.typography.subtitle1
        )
        AstraTextComposable(
            text = if (selected == null) "-" else toString.invoke(selected),
            color = MaterialTheme.colors.secondaryVariant,
            textAlign = TextAlign.End,
            modifier = Modifier
                .weight(1f),
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Preview
@Composable
private fun EnumOptionSelectedPreview() {
    AdaptThemeFadeComposable {
        FilterCardComposable {
            EnumOptionComposable(
                text = "Direction",
                selected = LayoutDirection.Ltr,
                toString = { direction -> direction.name.lowercase() },
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun EnumOptionUnselectedPreview() {
    AdaptThemeFadeComposable {
        FilterCardComposable {
            EnumOptionComposable<LayoutDirection>(
                text = "Direction",
                selected = null,
                toString = { direction -> direction.name.lowercase() },
                onClick = {}
            )
        }
    }
}
