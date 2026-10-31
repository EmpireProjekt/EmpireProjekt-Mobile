package com.makeevrserg.empireprojekt.mobile.core.ui.paging.composable

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.resources.common_action_reload
import com.makeevrserg.empireprojekt.mobile.core.resources.common_paging_empty
import com.makeevrserg.empireprojekt.mobile.core.resources.common_paging_last_page_desc
import com.makeevrserg.empireprojekt.mobile.core.resources.common_paging_network_error
import com.makeevrserg.empireprojekt.mobile.core.resources.img_splash
import com.makeevrserg.empireprojekt.mobile.core.ui.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.paging.model.PagingContent
import com.makeevrserg.empireprojekt.mobile.core.ui.text.composable.AstraTextComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asPainter

private const val SHIMMER_ITEMS_COUNT = 8

@Composable
private fun PagingMessageComposable(text: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = MR.images.img_splash.asPainter(),
            modifier = Modifier.size(96.dp),
            contentDescription = null
        )
        AstraTextComposable(
            text = text,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
internal fun PagingLastPageComposable() {
    PagingMessageComposable(MR.strings.common_paging_last_page_desc.asComposableString())
}

@Composable
internal fun PagingNoPagesComposable() {
    PagingMessageComposable(MR.strings.common_paging_empty.asComposableString())
}

@Composable
internal fun PagingNetworkErrorComposable(onReload: () -> Unit) {
    Column {
        PagingMessageComposable(MR.strings.common_paging_network_error.asComposableString())
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = onReload) {
                AstraTextComposable(
                    text = MR.strings.common_action_reload.asComposableString(),
                    modifier = Modifier.clickable(onClick = onReload),
                    style = MaterialTheme.typography.h6,
                    color = AppTheme.astraColors.astraLogo.astraOrange,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
internal fun PagingShimmerLoaderComposable(item: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS)) {
        repeat(times = SHIMMER_ITEMS_COUNT) {
            item.invoke()
        }
    }
}

@Composable
internal fun PagingFooterComposable(
    paging: PagingContent,
    onReload: () -> Unit,
    loader: @Composable () -> Unit
) {
    Crossfade(
        targetState = paging.isLoading,
        label = "crossfade loading indicator",
        content = { isLoadingShown ->
            if (!isLoadingShown) return@Crossfade
            loader.invoke()
        }
    )
    val isLastNonEmptyPage = paging.isLastPage && !paging.isEmpty
    val isLastEmptyPage = paging.isLastPage && paging.isEmpty
    when {
        isLastNonEmptyPage -> PagingLastPageComposable()
        isLastEmptyPage -> PagingNoPagesComposable()
        paging.isFailure -> PagingNetworkErrorComposable(onReload)
    }
}

@Composable
fun PagingListFooterComposable(
    paging: PagingContent,
    onReload: () -> Unit,
    shimmerItem: @Composable () -> Unit
) {
    PagingFooterComposable(
        paging = paging,
        onReload = onReload,
        loader = { PagingShimmerLoaderComposable(shimmerItem) }
    )
}

@Preview
@Composable
private fun PagingLastPagePreview() {
    AdaptThemeFadeComposable {
        PagingLastPageComposable()
    }
}

@Preview
@Composable
private fun PagingNoPagesPreview() {
    AdaptThemeFadeComposable {
        PagingNoPagesComposable()
    }
}

@Preview
@Composable
private fun PagingNetworkErrorPreview() {
    AdaptThemeFadeComposable {
        PagingNetworkErrorComposable(onReload = {})
    }
}
