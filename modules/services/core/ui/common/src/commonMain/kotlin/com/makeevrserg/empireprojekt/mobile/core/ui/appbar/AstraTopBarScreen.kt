package com.makeevrserg.empireprojekt.mobile.core.ui.appbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.text.AstraText
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade

@Composable
fun AstraTopBarScreen(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primaryVariant)
    ) {
        AstraCenterAlignedTopAppBar(
            title = title,
            onBackClick = onBackClick,
            actions = actions
        )
        content.invoke(this)
    }
}

@Preview
@Composable
private fun AstraTopBarScreenPreview() {
    AdaptThemeFade {
        AstraTopBarScreen(title = "МЕНЮ", onBackClick = {}) {
            AstraText(text = "Content")
        }
    }
}
