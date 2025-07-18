package com.makeevrserg.empireprojekt.mobile.core.ui.appbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asFontFamily
import com.makeevrserg.empireprojekt.mobile.services.core.PopComponent

@Composable
fun AstraCenterAlignedTopAppBar(
    title: String = "",
    popComponent: PopComponent,
    actions: @Composable RowScope.() -> Unit = {}
) {
    val popModel by popComponent.popModel.subscribeAsState()
    AstraCenterAlignedTopAppBar(
        title = title,
        onBackClicked = popModel.popActionOrNull,
        actions = actions
    )
}

@Composable
fun AstraCenterAlignedTopAppBar(
    title: String = "",
    onBackClicked: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    AstraCenterAlignedTopAppBar(
        onBackClicked = onBackClicked,
        actions = actions,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontFamily = MR.fonts.jetbrainsmono_wght.asFontFamily()
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AstraCenterAlignedTopAppBar(
    title: @Composable () -> Unit,
    onBackClicked: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        actions = actions,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent,
        ),
        title = title,
        navigationIcon = {
            onBackClicked?.let {
                IconButton(onClick = it) {
                    Icon(
                        imageVector = Icons.Default.ChevronLeft,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun AstraCenterAlignedTopAppBarPreview() {
    AstraCenterAlignedTopAppBar(
        title = "Hello World",
        onBackClicked = {},
        actions = {
            Box(Modifier.size(24.dp).background(Color.Red))
        }
    )
}
