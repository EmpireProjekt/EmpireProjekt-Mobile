package com.makeevrserg.empireprojekt.mobile.core.ui.paging.composable

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
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
import com.makeevrserg.empireprojekt.mobile.core.ui.paging.model.PagingContent
import com.makeevrserg.empireprojekt.mobile.core.ui.text.composable.AstraText
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asPainter

private const val SHIMMER_ITEMS_COUNT = 8

@Composable
private fun PagingMessage(text: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
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
internal fun PagingLastPage() {
    PagingMessage(MR.strings.common_paging_last_page_desc.asComposableString())
}

@Composable
internal fun PagingNoPages() {
    PagingMessage(MR.strings.common_paging_empty.asComposableString())
}

@Composable
internal fun PagingNetworkError(onReload: () -> Unit) {
    Column {
        PagingMessage(MR.strings.common_paging_network_error.asComposableString())
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Button(onClick = onReload) {
                AstraText(
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
internal fun PagingShimmerLoader(item: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS)) {
        repeat(times = SHIMMER_ITEMS_COUNT) {
            item.invoke()
        }
    }
}

@Composable
internal fun PagingFooter(
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
        isLastNonEmptyPage -> PagingLastPage()
        isLastEmptyPage -> PagingNoPages()
        paging.isFailure -> PagingNetworkError(onReload)
    }
}

fun LazyListScope.pagingFooter(
    paging: PagingContent,
    onReload: () -> Unit,
    shimmerItem: @Composable () -> Unit
) {
    item {
        PagingFooter(
            paging = paging,
            onReload = onReload,
            loader = { PagingShimmerLoader(shimmerItem) }
        )
    }
}

@Preview
@Composable
private fun PagingLastPagePreview() {
    AdaptThemeFade {
        PagingLastPage()
    }
}

@Preview
@Composable
private fun PagingNoPagesPreview() {
    AdaptThemeFade {
        PagingNoPages()
    }
}

@Preview
@Composable
private fun PagingNetworkErrorPreview() {
    AdaptThemeFade {
        PagingNetworkError(onReload = {})
    }
}
