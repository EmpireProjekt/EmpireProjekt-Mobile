package com.makeevrserg.empireprojekt.mobile.core.ui.appbar.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.makeevrserg.empireprojekt.mobile.core.ui.button.composable.AstraBackButtonComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.text.composable.AstraTextComposable
import com.makeevrserg.empireprojekt.mobile.services.core.navigation.component.PopComponent

@Composable
fun AstraCenterAlignedTopAppBarComposable(
    popComponent: PopComponent,
    modifier: Modifier = Modifier,
    title: String = "",
    actions: @Composable RowScope.() -> Unit = {}
) {
    val popModel by popComponent.popModel.subscribeAsState()
    AstraCenterAlignedTopAppBarComposable(
        title = title,
        onBackClick = popModel.popActionOrNull,
        modifier = modifier,
        actions = actions
    )
}

@Composable
fun AstraCenterAlignedTopAppBarComposable(
    modifier: Modifier = Modifier,
    title: String = "",
    onBackClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    AstraCenterAlignedTopAppBarComposable(
        onBackClick = onBackClick,
        modifier = modifier,
        actions = actions,
        title = {
            AstraTextComposable(
                text = title,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AstraCenterAlignedTopAppBarComposable(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        actions = actions,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent,
        ),
        title = title,
        navigationIcon = {
            onBackClick?.let { backClick ->
                AstraBackButtonComposable(onClick = backClick)
            }
        }
    )
}

@Preview
@Composable
private fun AstraCenterAlignedTopAppBarWithBackPreview() {
    AdaptThemeFadeComposable {
        AstraCenterAlignedTopAppBarComposable(
            title = "Hello World",
            onBackClick = {},
            actions = {
                Box(Modifier.size(24.dp).background(Color.Red))
            }
        )
    }
}

@Preview
@Composable
private fun AstraCenterAlignedTopAppBarTitleOnlyPreview() {
    AdaptThemeFadeComposable {
        AstraCenterAlignedTopAppBarComposable(title = "Hello World")
    }
}
