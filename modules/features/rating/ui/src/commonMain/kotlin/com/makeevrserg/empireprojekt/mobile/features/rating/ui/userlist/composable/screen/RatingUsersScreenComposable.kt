package com.makeevrserg.empireprojekt.mobile.features.rating.ui.userlist.composable.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.common.paging.composable.PagingLazyColumnComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.common.paging.composable.PagingListFooterComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.common.paging.model.PagingContent
import com.makeevrserg.empireprojekt.mobile.core.ui.common.searchbar.model.SearchBarState
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.theme.model.ComposeTheme
import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingUser
import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingsFilter
import com.makeevrserg.empireprojekt.mobile.features.rating.api.userlist.component.RatingUsersComponent
import com.makeevrserg.empireprojekt.mobile.features.rating.ui.userlist.composable.component.RatingUserShimmerWidgetComposable
import com.makeevrserg.empireprojekt.mobile.features.rating.ui.userlist.composable.component.RatingUserWidgetComposable
import com.makeevrserg.empireprojekt.mobile.features.rating.ui.userlist.composable.component.RatingUsersAppBarComposable
import com.makeevrserg.empireprojekt.mobile.features.rating.ui.userlist.composable.component.RatingsFilterCardComposable

@Suppress("LongMethod", "LongParameterList")
@Composable
internal fun RatingUsersScreenComposable(
    model: RatingUsersComponent.Model,
    onBack: (() -> Unit)?,
    onUpdateQuery: (String) -> Unit,
    onNameSortClick: () -> Unit,
    onLastUpdateSortClick: () -> Unit,
    onRatingSortClick: () -> Unit,
    onUserClick: (RatingUser) -> Unit,
    onLoadNextPage: () -> Unit,
    onReset: () -> Unit,
    modifier: Modifier = Modifier
) {
    var searchBarState by remember {
        val state = if (model.filter.query.isEmpty()) SearchBarState.Closed else SearchBarState.Open
        mutableStateOf(state)
    }
    Scaffold(
        modifier = modifier.animateContentSize(),
        topBar = {
            RatingUsersAppBarComposable(
                query = model.filter.query,
                searchBarState = searchBarState,
                onSearchBarStateChange = { state -> searchBarState = state },
                onUpdateQuery = onUpdateQuery,
                onBack = onBack
            )
        }
    ) { contentPadding ->
        PagingLazyColumnComposable(
            onLoadNextPage = onLoadNextPage,
            contentPadding = contentPadding
        ) {
            item {
                RatingsFilterCardComposable(
                    filter = model.filter,
                    onNameSortClick = onNameSortClick,
                    onLastUpdateSortClick = onLastUpdateSortClick,
                    onRatingSortClick = onRatingSortClick
                )
            }
            items(model.items) { ratingUserModel ->
                RatingUserWidgetComposable(
                    model = ratingUserModel,
                    onClick = { onUserClick.invoke(ratingUserModel) }
                )
            }
            item {
                PagingListFooterComposable(
                    paging = PagingContent(
                        isEmpty = model.items.isEmpty(),
                        isLastPage = model.isLastPage,
                        isLoading = model.isLoading,
                        isFailure = model.isFailure
                    ),
                    onReload = onReset,
                    shimmerItem = { RatingUserShimmerWidgetComposable() }
                )
            }
        }
    }
}

@Suppress("MagicNumber")
private fun ratingUsersPreviewItems(): List<RatingUser> {
    return listOf(
        RatingUser(
            id = 1,
            minecraftUUID = "uuid-1",
            minecraftName = "RomaRoman",
            lastUpdated = 1706549308031,
            totalRating = 12,
            ratingVotes = 8
        ),
        RatingUser(
            id = 2,
            minecraftUUID = "uuid-2",
            minecraftName = "cinnamonrein",
            lastUpdated = 1706549308031,
            totalRating = -3,
            ratingVotes = 5
        )
    )
}

@Preview
@Composable
private fun RatingUsersComposableScreenLoadedPreview() {
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.DARK) {
        RatingUsersScreenComposable(
            model = RatingUsersComponent.Model(
                items = ratingUsersPreviewItems(),
                filter = RatingsFilter(),
                isLoading = false,
                isFailure = false,
                isLastPage = false
            ),
            onBack = {},
            onUpdateQuery = {},
            onNameSortClick = {},
            onLastUpdateSortClick = {},
            onRatingSortClick = {},
            onUserClick = {},
            onLoadNextPage = {},
            onReset = {}
        )
    }
}

@Preview
@Composable
private fun RatingUsersComposableScreenLoadingPreview() {
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.DARK) {
        RatingUsersScreenComposable(
            model = RatingUsersComponent.Model(
                items = emptyList(),
                filter = RatingsFilter(),
                isLoading = true,
                isFailure = false,
                isLastPage = false
            ),
            onBack = {},
            onUpdateQuery = {},
            onNameSortClick = {},
            onLastUpdateSortClick = {},
            onRatingSortClick = {},
            onUserClick = {},
            onLoadNextPage = {},
            onReset = {}
        )
    }
}

@Preview
@Composable
private fun RatingUsersComposableScreenEmptyPreview() {
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.DARK) {
        RatingUsersScreenComposable(
            model = RatingUsersComponent.Model(
                items = emptyList(),
                filter = RatingsFilter(),
                isLoading = false,
                isFailure = false,
                isLastPage = true
            ),
            onBack = {},
            onUpdateQuery = {},
            onNameSortClick = {},
            onLastUpdateSortClick = {},
            onRatingSortClick = {},
            onUserClick = {},
            onLoadNextPage = {},
            onReset = {}
        )
    }
}

@Preview
@Composable
private fun RatingUsersComposableScreenFailurePreview() {
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.DARK) {
        RatingUsersScreenComposable(
            model = RatingUsersComponent.Model(
                items = ratingUsersPreviewItems(),
                filter = RatingsFilter(),
                isLoading = false,
                isFailure = true,
                isLastPage = false
            ),
            onBack = {},
            onUpdateQuery = {},
            onNameSortClick = {},
            onLastUpdateSortClick = {},
            onRatingSortClick = {},
            onUserClick = {},
            onLoadNextPage = {},
            onReset = {}
        )
    }
}
