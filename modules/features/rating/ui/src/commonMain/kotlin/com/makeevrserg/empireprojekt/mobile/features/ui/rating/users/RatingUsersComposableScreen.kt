package com.makeevrserg.empireprojekt.mobile.features.ui.rating.users

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.paging.PagingLazyColumn
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
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
        PagingLazyColumn(
            items = model.items,
            isLastPage = model.isLastPage,
            isLoading = model.isLoading,
            isFailure = model.isFailure,
            onLoadNextPage = onLoadNextPage,
            onReload = onReset,
            shimmerItem = { RatingUserShimmerWidget() },
            contentPadding = contentPadding,
            header = {
                RatingsFilterCard(
                    filter = model.filter,
                    onNameSortClick = onNameSortClick,
                    onLastUpdateSortClick = onLastUpdateSortClick,
                    onRatingSortClick = onRatingSortClick
                )
            },
            itemContent = { ratingUserModel ->
                RatingUserWidget(
                    model = ratingUserModel,
                    onClick = { onUserClick(ratingUserModel) }
                )
            }
        )
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
