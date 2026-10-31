package com.makeevrserg.empireprojekt.mobile.features.ui.rating.userlist

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
import com.makeevrserg.empireprojekt.mobile.core.ui.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.paging.composable.PagingLazyColumnComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.paging.composable.PagingListFooterComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.paging.model.PagingContent
import com.makeevrserg.empireprojekt.mobile.core.ui.searchbar.model.SearchBarState
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.model.ComposeTheme
import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingUser
import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingsFilter
import com.makeevrserg.empireprojekt.mobile.features.rating.api.userlist.component.RatingUsersComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.userlist.composable.component.RatingUserShimmerWidget
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.userlist.composable.component.RatingUserWidget
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.userlist.composable.component.RatingUsersAppBar
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.userlist.composable.component.RatingsFilterCard

@Suppress("LongMethod", "LongParameterList")
@Composable
internal fun RatingUsersComposableScreen(
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
            RatingUsersAppBar(
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
                RatingsFilterCard(
                    filter = model.filter,
                    onNameSortClick = onNameSortClick,
                    onLastUpdateSortClick = onLastUpdateSortClick,
                    onRatingSortClick = onRatingSortClick
                )
            }
            items(model.items) { ratingUserModel ->
                RatingUserWidget(
                    model = ratingUserModel,
                    onClick = { onUserClick(ratingUserModel) }
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
                    shimmerItem = { RatingUserShimmerWidget() }
                )
            }
        }
    }
}

@Suppress("MagicNumber")
private fun ratingUsersPreviewItems(): List<RatingUser> = listOf(
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

@Preview
@Composable
private fun RatingUsersComposableScreenLoadedPreview() {
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.DARK) {
        RatingUsersComposableScreen(
            model = RatingUsersComponent.Model(
                items = ratingUsersPreviewItems(),
                filter = RatingsFilter()
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
        RatingUsersComposableScreen(
            model = RatingUsersComponent.Model(
                items = emptyList(),
                filter = RatingsFilter(),
                isLoading = true
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
        RatingUsersComposableScreen(
            model = RatingUsersComponent.Model(
                items = emptyList(),
                filter = RatingsFilter(),
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
        RatingUsersComposableScreen(
            model = RatingUsersComponent.Model(
                items = ratingUsersPreviewItems(),
                filter = RatingsFilter(),
                isFailure = true
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
