package com.makeevrserg.empireprojekt.mobile.core.ui.appbar.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.text.composable.AstraTextComposable

@Composable
fun AstraTopBarScreenComposable(
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
        AstraCenterAlignedTopAppBarComposable(
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
    AdaptThemeFadeComposable {
        AstraTopBarScreenComposable(
            title = "МЕНЮ",
            onBackClick = {}
        ) {
            AstraTextComposable(text = "Content")
        }
    }
}
