package com.makeevrserg.empireprojekt.mobile.core.ui.common.paging.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.common.paging.model.PagingContent
import com.makeevrserg.empireprojekt.mobile.core.ui.common.placeholder.composable.ShimmerCardComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.common.placeholder.composable.ShimmerLineComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.common.text.composable.AstraTextComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.common.util.navBarsPadding
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.theme.AppTheme
import kotlinx.coroutines.flow.filter

@Composable
private fun LazyListState.OnEndReachedComposable(onLoadMore: () -> Unit) {
    val currentOnLoadMore by rememberUpdatedState(onLoadMore)
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = layoutInfo.visibleItemsInfo
                .lastOrNull()
                ?: return@derivedStateOf true
            lastVisibleItem.index == layoutInfo.totalItemsCount - 1
        }
    }

    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }
            .filter { isEndReached -> isEndReached }
            .collect { currentOnLoadMore.invoke() }
    }
}

@Composable
fun PagingLazyColumnComposable(
    onLoadNextPage: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: LazyListScope.() -> Unit,
) {
    val lazyListState = rememberLazyListState()
    lazyListState.OnEndReachedComposable(onLoadNextPage)
    LazyColumn(
        modifier = modifier.padding(horizontal = AppTheme.dimens.XS),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS),
        contentPadding = contentPadding,
        state = lazyListState
    ) {
        content.invoke(this)
        item { Spacer(Modifier.navBarsPadding()) }
    }
}

private val PreviewNames = listOf("RomaRoman", "cinnamonrein")

@Composable
private fun PagingLazyColumnPreviewContent(paging: PagingContent) {
    val names = if (paging.isEmpty) emptyList() else PreviewNames
    PagingLazyColumnComposable(onLoadNextPage = {}) {
        items(names) { name -> AstraTextComposable(text = name) }
        item {
            PagingListFooterComposable(
                paging = paging,
                onReload = {},
                shimmerItem = { ShimmerCardComposable { ShimmerLineComposable(widthFraction = 0.5f) } }
            )
        }
    }
}

@Preview
@Composable
private fun PagingLazyColumnLoadedPreview() {
    AdaptThemeFadeComposable {
        PagingLazyColumnPreviewContent(
            paging = PagingContent(
                isEmpty = false,
                isLastPage = false,
                isLoading = false,
                isFailure = false
            )
        )
    }
}

@Preview
@Composable
private fun PagingLazyColumnLoadingPreview() {
    AdaptThemeFadeComposable {
        PagingLazyColumnPreviewContent(
            paging = PagingContent(
                isEmpty = false,
                isLastPage = false,
                isLoading = true,
                isFailure = false
            )
        )
    }
}

@Preview
@Composable
private fun PagingLazyColumnLastPagePreview() {
    AdaptThemeFadeComposable {
        PagingLazyColumnPreviewContent(
            paging = PagingContent(
                isEmpty = false,
                isLastPage = true,
                isLoading = false,
                isFailure = false
            )
        )
    }
}

@Preview
@Composable
private fun PagingLazyColumnEmptyPreview() {
    AdaptThemeFadeComposable {
        PagingLazyColumnPreviewContent(
            paging = PagingContent(
                isEmpty = true,
                isLastPage = true,
                isLoading = false,
                isFailure = false
            )
        )
    }
}

@Preview
@Composable
private fun PagingLazyColumnFailurePreview() {
    AdaptThemeFadeComposable {
        PagingLazyColumnPreviewContent(
            paging = PagingContent(
                isEmpty = false,
                isLastPage = false,
                isLoading = false,
                isFailure = true
            )
        )
    }
}
