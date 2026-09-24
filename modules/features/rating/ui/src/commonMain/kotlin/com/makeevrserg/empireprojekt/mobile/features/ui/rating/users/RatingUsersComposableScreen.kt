package com.makeevrserg.empireprojekt.mobile.features.ui.rating.users

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.paging.OnEndReached
import com.makeevrserg.empireprojekt.mobile.core.ui.paging.PagingWidget
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.ComposeTheme
import com.makeevrserg.empireprojekt.mobile.features.rating.users.presentation.RatingUsersComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.components.RatingUserShimmerWidget
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.components.RatingUserWidget
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.components.RatingUsersAppBar
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.components.RatingsFilterCard
import ru.astrainteractive.empireapi.models.rating.RatingUserModel
import ru.astrainteractive.empireapi.models.rating.RatingsFilterModel

@Suppress("LongMethod", "LongParameterList")
@Composable
internal fun RatingUsersComposableScreen(
    model: RatingUsersComponent.Model,
    onBack: (() -> Unit)?,
    onUpdateQuery: (String) -> Unit,
    onNameSortClick: () -> Unit,
    onLastUpdateSortClick: () -> Unit,
    onRatingSortClick: () -> Unit,
    onUserClick: (RatingUserModel) -> Unit,
    onLoadNextPage: () -> Unit,
    onReset: () -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()
    lazyListState.OnEndReached { onLoadNextPage() }

    Scaffold(
        modifier = modifier.animateContentSize(),
        topBar = {
            RatingUsersAppBar(
                query = model.filter.query,
                onUpdateQuery = onUpdateQuery,
                onBack = onBack
            )
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier.padding(horizontal = AppTheme.dimens.XS),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS),
            contentPadding = contentPadding,
            state = lazyListState
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
                PagingWidget.Auto(
                    list = model.items,
                    isLastPage = model.isLastPage,
                    isLoading = model.isLoading,
                    isFailure = model.isFailure,
                    onReload = onReset,
                    loader = { PagingWidget.ShimmerLoader { RatingUserShimmerWidget() } }
                )
            }
        }
    }
}

@Suppress("MagicNumber")
private fun ratingUsersPreviewItems(): List<RatingUserModel> = listOf(
    RatingUserModel(
        id = 1,
        minecraftUUID = "uuid-1",
        minecraftName = "RomaRoman",
        lastUpdated = 1706549308031,
        totalRating = 12,
        ratingVotes = 8
    ),
    RatingUserModel(
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
    AdaptThemeFade(composeTheme = ComposeTheme.DARK) {
        RatingUsersComposableScreen(
            model = RatingUsersComponent.Model(
                items = ratingUsersPreviewItems(),
                filter = RatingsFilterModel()
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
    AdaptThemeFade(composeTheme = ComposeTheme.DARK) {
        RatingUsersComposableScreen(
            model = RatingUsersComponent.Model(
                items = emptyList(),
                filter = RatingsFilterModel(),
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
    AdaptThemeFade(composeTheme = ComposeTheme.DARK) {
        RatingUsersComposableScreen(
            model = RatingUsersComponent.Model(
                items = emptyList(),
                filter = RatingsFilterModel(),
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
    AdaptThemeFade(composeTheme = ComposeTheme.DARK) {
        RatingUsersComposableScreen(
            model = RatingUsersComponent.Model(
                items = ratingUsersPreviewItems(),
                filter = RatingsFilterModel(),
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
