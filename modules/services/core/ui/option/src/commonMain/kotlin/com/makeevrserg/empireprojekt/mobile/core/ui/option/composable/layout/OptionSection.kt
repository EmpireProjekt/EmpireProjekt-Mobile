package com.makeevrserg.empireprojekt.mobile.core.ui.option.composable.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.option.composable.component.OptionHref
import com.makeevrserg.empireprojekt.mobile.core.ui.option.composable.component.OptionSeparator
import com.makeevrserg.empireprojekt.mobile.core.ui.option.model.OptionSectionContent
import com.makeevrserg.empireprojekt.mobile.core.ui.option.theme.OptionSpacing
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.util.astraCard

@Composable
fun OptionSection(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .astraCard(color = MaterialTheme.colors.primary),
        contentAlignment = Alignment.Center,
        content = content
    )
}

@Composable
fun <T> OptionSection(
    section: OptionSectionContent<T>,
    modifier: Modifier = Modifier,
    item: @Composable (T) -> Unit
) {
    OptionSection(modifier = modifier) {
        Column {
            section.items.forEachIndexed { index, value ->
                item.invoke(value)
                if (index != section.items.lastIndex) {
                    OptionSeparator(Modifier.fillMaxWidth())
                }
            }
        }
    }
}

private val PreviewSection = OptionSectionContent(
    items = listOf(
        "Github",
        "Discord",
        "Telegram"
    )
)

@Preview
@Composable
private fun OptionSectionItemsPreview() {
    AdaptThemeFade {
        OptionSection(section = PreviewSection) { title ->
            OptionHref(
                text = title,
                onClick = {},
                icon = rememberVectorPainter(Icons.Filled.Image),
                contentPadding = OptionSpacing.ContentPadding
            )
        }
    }
}
