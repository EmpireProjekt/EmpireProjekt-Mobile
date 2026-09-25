package com.makeevrserg.empireprojekt.mobile.features.ui.status

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
import com.makeevrserg.empireprojekt.mobile.core.ui.appbar.AstraCenterAlignedTopAppBar
import com.makeevrserg.empireprojekt.mobile.core.ui.text.AstraText
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asComposableString
import com.makeevrserg.empireprojekt.mobile.features.status.url.presentation.UrlStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.status.widget.StatusWidget
import com.makeevrserg.empireprojekt.mobile.status.SR
import com.makeevrserg.empireprojekt.mobile.status.status_network_desc
import com.makeevrserg.empireprojekt.mobile.status.status_network_title
import dev.icerock.moko.resources.desc.Raw
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Suppress("UnstableCollections")
@Composable
internal fun StatusComposableScreen(
    statusComponents: List<UrlStatusComponent>,
    onThemeClick: () -> Unit,
    onInfoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AstraCenterAlignedTopAppBar(title = SR.strings.status_network_title.asComposableString()) {
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
                onClick = {
                    onInfoClick.invoke()
                },
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
                AstraText(
                    text = SR.strings.status_network_desc.asComposableString(),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary.copy(alpha = .5f)
                )
            }
            items(statusComponents) { statusComponent ->
                StatusWidget(statusComponent)
            }
        }
    }
}

private class PreviewUrlStatusComponent(
    title: String,
    status: UrlStatusComponent.LoadingStatus
) : UrlStatusComponent {
    override val model: StateFlow<UrlStatusComponent.Model> = MutableStateFlow(
        UrlStatusComponent.Model(
            title = StringDesc.Raw(title),
            isLoading = status == UrlStatusComponent.LoadingStatus.LOADING,
            status = status
        )
    )

    override fun checkStatus() = Unit
}

@Preview
@Composable
private fun StatusComposableScreenPreview() {
    AdaptThemeFade {
        StatusComposableScreen(
            statusComponents = listOf(
                PreviewUrlStatusComponent(
                    title = "Empire Survival",
                    status = UrlStatusComponent.LoadingStatus.SUCCESS
                ),
                PreviewUrlStatusComponent(
                    title = "Empire Site",
                    status = UrlStatusComponent.LoadingStatus.LOADING
                ),
                PreviewUrlStatusComponent(
                    title = "Empire Map",
                    status = UrlStatusComponent.LoadingStatus.ERROR
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
    AdaptThemeFade {
        StatusComposableScreen(
            statusComponents = emptyList(),
            onThemeClick = {},
            onInfoClick = {}
        )
    }
}
