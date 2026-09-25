package com.makeevrserg.empireprojekt.mobile.core.ui.paging

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.common.navBarsPadding
import com.makeevrserg.empireprojekt.mobile.core.ui.placeholder.ShimmerCard
import com.makeevrserg.empireprojekt.mobile.core.ui.placeholder.ShimmerLine
import com.makeevrserg.empireprojekt.mobile.core.ui.text.AstraText
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Suppress("UnstableCollections", "LongParameterList")
@Composable
fun <T> PagingLazyColumn(
    items: List<T>,
    isLastPage: Boolean,
    isLoading: Boolean,
    isFailure: Boolean,
    onLoadNextPage: () -> Unit,
    onReload: () -> Unit,
    shimmerItem: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    header: (@Composable LazyItemScope.() -> Unit)? = null,
    itemContent: @Composable LazyItemScope.(T) -> Unit,
) {
    val lazyListState = rememberLazyListState()
    lazyListState.OnEndReached { onLoadNextPage() }
    LazyColumn(
        modifier = modifier.padding(horizontal = AppTheme.dimens.XS),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS),
        contentPadding = contentPadding,
        state = lazyListState
    ) {
        header?.let { headerContent ->
            item { headerContent.invoke(this) }
        }
        items(items) { value -> itemContent.invoke(this, value) }
        item {
            PagingWidget.Auto(
                list = items,
                isLastPage = isLastPage,
                isLoading = isLoading,
                isFailure = isFailure,
                onReload = onReload,
                loader = { PagingWidget.ShimmerLoader { shimmerItem.invoke() } }
            )
        }
        item { Spacer(Modifier.navBarsPadding()) }
    }
}

@Suppress("MagicNumber")
@Preview
@Composable
private fun PagingLazyColumnLoadingPreview() {
    AdaptThemeFade {
        PagingLazyColumn(
            items = listOf("RomaRoman", "cinnamonrein"),
            isLastPage = false,
            isLoading = true,
            isFailure = false,
            onLoadNextPage = {},
            onReload = {},
            shimmerItem = { ShimmerCard { ShimmerLine(widthFraction = 0.5f) } },
            itemContent = { name -> AstraText(text = name) }
        )
    }
}
