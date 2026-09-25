package com.makeevrserg.empireprojekt.mobile.core.ui.filtercard

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
import com.makeevrserg.empireprojekt.mobile.core.ui.text.AstraText
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Composable
fun <E : Enum<E>> FilterCardScope.EnumOption(
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
        AstraText(
            text = text,
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Start,
            modifier = Modifier,
            style = MaterialTheme.typography.subtitle1
        )
        AstraText(
            text = selected?.let { toString.invoke(it) } ?: "-",
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
    AdaptThemeFade {
        FilterCard {
            EnumOption(
                text = "Direction",
                selected = LayoutDirection.Ltr,
                toString = { direction -> direction.name },
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun EnumOptionUnselectedPreview() {
    AdaptThemeFade {
        FilterCard {
            EnumOption<LayoutDirection>(
                text = "Direction",
                selected = null,
                toString = { direction -> direction.name },
                onClick = {}
            )
        }
    }
}
