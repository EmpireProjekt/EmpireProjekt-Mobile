package com.makeevrserg.empireprojekt.mobile.core.ui.paging

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.resources.common_action_reload
import com.makeevrserg.empireprojekt.mobile.core.resources.common_paging_empty
import com.makeevrserg.empireprojekt.mobile.core.resources.common_paging_last_page_desc
import com.makeevrserg.empireprojekt.mobile.core.resources.common_paging_network_error
import com.makeevrserg.empireprojekt.mobile.core.resources.img_splash
import com.makeevrserg.empireprojekt.mobile.core.ui.placeholder.LoadingContent
import com.makeevrserg.empireprojekt.mobile.core.ui.text.AstraText
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asPainter

private const val SHIMMER_ITEMS_COUNT = 8

object PagingWidget {
    @Suppress("ModifierMissing")
    @Composable
    fun Base(text: String) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = MR.images.img_splash.asPainter(),
                modifier = Modifier.size(96.dp),
                contentDescription = null
            )
            AstraText(
                text = text,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Center
            )
        }
    }

    @Composable
    fun LastPage() {
        Base(MR.strings.common_paging_last_page_desc.asComposableString())
    }

    @Composable
    fun NoPages() {
        Base(MR.strings.common_paging_empty.asComposableString())
    }

    @Composable
    fun NetworkError(
        onReload: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        Column(modifier = modifier) {
            Base(MR.strings.common_paging_network_error.asComposableString())
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Button(onClick = onReload) {
                    AstraText(
                        text = MR.strings.common_action_reload.asComposableString(),
                        modifier = Modifier.clickable { onReload.invoke() },
                        style = MaterialTheme.typography.h6,
                        color = AppTheme.astraColors.astraLogo.astraOrange,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

    @Suppress("ModifierMissing")
    @Composable
    fun Loading() {
        LoadingContent(Modifier.fillMaxWidth())
    }

    @Composable
    fun ShimmerLoader(
        modifier: Modifier = Modifier,
        item: @Composable () -> Unit
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS)
        ) {
            repeat(times = SHIMMER_ITEMS_COUNT) {
                item()
            }
        }
    }

    @Suppress("UnstableCollections")
    @Composable
    fun Auto(
        list: List<*>,
        isLastPage: Boolean,
        isLoading: Boolean,
        isFailure: Boolean,
        onReload: () -> Unit,
        loader: @Composable () -> Unit = { Loading() }
    ) {
        Crossfade(
            targetState = isLoading,
            label = "crossfade loading indicator",
            content = { isLoading ->
                if (isLoading) {
                    loader.invoke()
                }
            }
        )

        if (isLastPage && list.isNotEmpty()) {
            LastPage()
        } else if (isLastPage && list.isEmpty()) {
            NoPages()
        } else if (isFailure) {
            NetworkError(onReload)
        }
    }
}
