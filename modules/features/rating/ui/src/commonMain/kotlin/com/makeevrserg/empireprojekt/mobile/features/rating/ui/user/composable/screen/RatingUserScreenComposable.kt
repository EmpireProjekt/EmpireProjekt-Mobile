package com.makeevrserg.empireprojekt.mobile.features.rating.ui.user.composable.screen

import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.common.appbar.composable.AstraCenterAlignedTopAppBarComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.common.paging.composable.PagingLazyColumnComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.common.paging.composable.PagingListFooterComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.common.paging.model.PagingContent
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.model.ComposeTheme
import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.Rating
import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingUser
import com.makeevrserg.empireprojekt.mobile.features.rating.api.user.model.RatingUserState
import com.makeevrserg.empireprojekt.mobile.features.rating.ui.user.composable.component.RatingUserWidgetComposable
import com.makeevrserg.empireprojekt.mobile.features.rating.ui.userlist.composable.component.RatingUserShimmerWidgetComposable
import kotlin.time.Instant

@Suppress("LongMethod")
@Composable
internal fun RatingUserScreenComposable(
    model: RatingUserState,
    onBack: (() -> Unit)?,
    onLoadNextPage: () -> Unit,
    onReset: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AstraCenterAlignedTopAppBarComposable(
                title = model.reviewedUserName,
                onBackClick = onBack
            )
        }
    ) { contentPadding ->
        PagingLazyColumnComposable(
            onLoadNextPage = onLoadNextPage,
            contentPadding = contentPadding
        ) {
            items(model.items) { ratingModel ->
                RatingUserWidgetComposable(
                    uuid = ratingModel.userCreatedReport?.minecraftUUID,
                    name = ratingModel.userCreatedReport?.minecraftName,
                    rating = ratingModel.rating,
                    message = ratingModel.message,
                    time = Instant.fromEpochMilliseconds(ratingModel.time)
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
private fun ratingUserPreviewModel(
    reviewedUserName: String,
    items: List<Rating>,
    isLoading: Boolean,
    isFailure: Boolean,
    isLastPage: Boolean
): RatingUserState {
    return RatingUserState(
        items = items,
        reviewedUserName = reviewedUserName,
        isLoading = isLoading,
        isFailure = isFailure,
        isLastPage = isLastPage
    )
}

@Suppress("MagicNumber")
private fun ratingUserPreviewItems(): List<Rating> {
    val reviewer = RatingUser(
        id = 2,
        minecraftUUID = "uuid-2",
        minecraftName = "cinnamonrein",
        lastUpdated = 1706549308031,
        totalRating = 4,
        ratingVotes = 3
    )
    val reviewed = RatingUser(
        id = 1,
        minecraftUUID = "uuid-1",
        minecraftName = "RomaRoman",
        lastUpdated = 1706549308031,
        totalRating = 12,
        ratingVotes = 8
    )
    return listOf(
        Rating(
            userCreatedReport = reviewer,
            reportedUser = reviewed,
            rating = 1,
            ratingTypeIndex = 0,
            message = "Great player, very helpful!",
            time = 1706549308031
        ),
        Rating(
            userCreatedReport = reviewer,
            reportedUser = reviewed,
            rating = -1,
            ratingTypeIndex = 0,
            message = "Griefed my base.",
            time = 1706549308031
        )
    )
}

@Preview
@Composable
private fun RatingUserComposableScreenLoadedPreview() {
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.DARK) {
        RatingUserScreenComposable(
            model = ratingUserPreviewModel(
                reviewedUserName = "RomaRoman",
                items = ratingUserPreviewItems(),
                isLoading = false,
                isFailure = false,
                isLastPage = false
            ),
            onBack = {},
            onLoadNextPage = {},
            onReset = {}
        )
    }
}

@Preview
@Composable
private fun RatingUserComposableScreenLoadingPreview() {
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.DARK) {
        RatingUserScreenComposable(
            model = ratingUserPreviewModel(
                reviewedUserName = "RomaRoman",
                items = emptyList(),
                isLoading = true,
                isFailure = false,
                isLastPage = false
            ),
            onBack = {},
            onLoadNextPage = {},
            onReset = {}
        )
    }
}

@Preview
@Composable
private fun RatingUserComposableScreenEmptyPreview() {
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.DARK) {
        RatingUserScreenComposable(
            model = ratingUserPreviewModel(
                reviewedUserName = "RomaRoman",
                items = emptyList(),
                isLoading = false,
                isFailure = false,
                isLastPage = true
            ),
            onBack = {},
            onLoadNextPage = {},
            onReset = {}
        )
    }
}

@Preview
@Composable
private fun RatingUserComposableScreenFailurePreview() {
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.DARK) {
        RatingUserScreenComposable(
            model = ratingUserPreviewModel(
                reviewedUserName = "RomaRoman",
                items = emptyList(),
                isLoading = false,
                isFailure = true,
                isLastPage = false
            ),
            onBack = {},
            onLoadNextPage = {},
            onReset = {}
        )
    }
}
