package com.makeevrserg.empireprojekt.mobile.features.status.ui.composable.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.common.appbar.composable.AstraCenterAlignedTopAppBarComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.common.text.composable.AstraTextComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.common.util.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.status.api.url.component.UrlStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.api.url.model.UrlStatusState
import com.makeevrserg.empireprojekt.mobile.features.status.ui.component.PreviewUrlStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.ui.composable.component.StatusWidgetComposable
import com.makeevrserg.empireprojekt.mobile.status.SR
import com.makeevrserg.empireprojekt.mobile.status.status_network_desc
import com.makeevrserg.empireprojekt.mobile.status.status_network_title

@Suppress("UnstableCollections")
@Composable
internal fun StatusScreenComposable(
    statusComponents: List<UrlStatusComponent>,
    onThemeClick: () -> Unit,
    onInfoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AstraCenterAlignedTopAppBarComposable(title = SR.strings.status_network_title.asComposableString()) {
                Icon(
                    imageVector = Icons.Filled.WbSunny,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onPrimary,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            onThemeClick.invoke()
                        }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colors.secondaryVariant,
                onClick = onInfoClick,
            ) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = null,
                    tint = AppTheme.astraColors.surface.onSecondaryVariant
                )
            }
        },
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = AppTheme.dimens.S)
                .navigationBarsPadding(),
            contentPadding = contentPadding,
        ) {
            item {
                AstraTextComposable(
                    text = SR.strings.status_network_desc.asComposableString(),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary.copy(alpha = .5f)
                )
            }
            items(statusComponents) { statusComponent ->
                StatusWidgetComposable(statusComponent = statusComponent)
            }
        }
    }
}

@Preview
@Composable
private fun StatusComposableScreenPreview() {
    AdaptThemeFadeComposable {
        StatusScreenComposable(
            statusComponents = listOf(
                PreviewUrlStatusComponent(
                    title = "Empire Survival",
                    status = UrlStatusState.LoadingStatus.SUCCESS
                ),
                PreviewUrlStatusComponent(
                    title = "Empire Site",
                    status = UrlStatusState.LoadingStatus.LOADING
                ),
                PreviewUrlStatusComponent(
                    title = "Empire Map",
                    status = UrlStatusState.LoadingStatus.ERROR
                )
            ),
            onThemeClick = {},
            onInfoClick = {}
        )
    }
}

@Preview
@Composable
private fun StatusComposableScreenEmptyPreview() {
    AdaptThemeFadeComposable {
        StatusScreenComposable(
            statusComponents = emptyList(),
            onThemeClick = {},
            onInfoClick = {}
        )
    }
}
