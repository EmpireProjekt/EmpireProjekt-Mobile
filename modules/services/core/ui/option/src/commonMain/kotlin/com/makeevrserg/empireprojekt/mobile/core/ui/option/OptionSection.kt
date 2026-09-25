package com.makeevrserg.empireprojekt.mobile.core.ui.option

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.common.astraCard
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade

@Composable
fun OptionSection(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .astraCard(),
        contentAlignment = Alignment.Center,
        content = {
            content.invoke(this)
        }
    )
}

@Suppress("UnstableCollections")
@Composable
fun <T> OptionSection(
    items: List<T>,
    modifier: Modifier = Modifier,
    item: @Composable (T) -> Unit
) {
    OptionSection(modifier = modifier) {
        Column {
            items.forEachIndexed { index, value ->
                item.invoke(value)
                if (index != items.lastIndex) {
                    OptionSeparator(Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@Preview
@Composable
private fun OptionSectionItemsPreview() {
    AdaptThemeFade {
        OptionSection(items = listOf("Github", "Discord", "Telegram")) { title ->
            OptionHref(
                text = title,
                onClick = {},
                icon = rememberVectorPainter(Icons.Filled.Image),
                contentPadding = OptionDefaults.ContentPadding
            )
        }
    }
}
