package com.makeevrserg.empireprojekt.mobile.features.ui.rating.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.appbar.AstraCenterAlignedTopAppBar
import com.makeevrserg.empireprojekt.mobile.core.ui.common.navBarsPadding
import com.makeevrserg.empireprojekt.mobile.core.ui.paging.OnEndReached
import com.makeevrserg.empireprojekt.mobile.core.ui.paging.PagingWidget
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.ComposeTheme
import com.makeevrserg.empireprojekt.mobile.features.rating.user.presentation.RatingUserComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.user.components.RatingUserWidget
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.components.RatingUserShimmerWidget
import ru.astrainteractive.empireapi.models.rating.RatingModel
import ru.astrainteractive.empireapi.models.rating.RatingUserModel
import ru.astrainteractive.empireapi.models.rating.UserRatingsRequest

@Suppress("LongMethod")
@Composable
internal fun RatingUserComposableScreen(
    model: RatingUserComponent.Model,
    onBack: (() -> Unit)?,
    onLoadNextPage: () -> Unit,
    onReset: () -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()
    lazyListState.OnEndReached { onLoadNextPage() }

    Scaffold(
        modifier = modifier,
        topBar = {
            AstraCenterAlignedTopAppBar(
                title = model.reviewedUserName,
                onBackClick = onBack
            )
        }
    ) { contentPadding ->
        LazyColumn(
            contentPadding = contentPadding,
            modifier = Modifier.padding(horizontal = AppTheme.dimens.XS),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS),
            state = lazyListState
        ) {
            items(model.items) { ratingModel ->
                RatingUserWidget(
                    uuid = ratingModel.userCreatedReport?.minecraftUUID,
                    name = ratingModel.userCreatedReport?.minecraftName,
                    rating = ratingModel.rating,
                    message = ratingModel.message,
                    time = ratingModel.time
                )
            }
            item {
                PagingWidget.Auto(
                    list = model.items,
                    isLastPage = model.isLastPage,
                    isLoading = model.isLoading,
                    isFailure = model.isFailure,
                    onReload = onReset,
                    loader = {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS),
                            content = {
                                repeat(times = 8) {
                                    RatingUserShimmerWidget()
                                }
                            }
                        )
                    }
                )
            }
            item { Spacer(Modifier.navBarsPadding()) }
        }
    }
}

@Suppress("MagicNumber")
private fun ratingUserPreviewModel(
    reviewedUserName: String,
    items: List<RatingModel>,
    isLoading: Boolean,
    isFailure: Boolean,
    isLastPage: Boolean
): RatingUserComponent.Model = RatingUserComponent.Model(
    items = items,
    request = UserRatingsRequest(id = 1),
    reviewedUserName = reviewedUserName,
    isLoading = isLoading,
    isFailure = isFailure,
    isLastPage = isLastPage
)

@Suppress("MagicNumber")
private fun ratingUserPreviewItems(): List<RatingModel> {
    val reviewer = RatingUserModel(
        id = 2,
        minecraftUUID = "uuid-2",
        minecraftName = "cinnamonrein",
        lastUpdated = 1706549308031,
        totalRating = 4,
        ratingVotes = 3
    )
    val reviewed = RatingUserModel(
        id = 1,
        minecraftUUID = "uuid-1",
        minecraftName = "RomaRoman",
        lastUpdated = 1706549308031,
        totalRating = 12,
        ratingVotes = 8
    )
    return listOf(
        RatingModel(
            userCreatedReport = reviewer,
            reportedUser = reviewed,
            rating = 1,
            ratingTypeIndex = 0,
            message = "Great player, very helpful!",
            time = 1706549308031
        ),
        RatingModel(
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
    AdaptThemeFade(composeTheme = ComposeTheme.DARK) {
        RatingUserComposableScreen(
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
    AdaptThemeFade(composeTheme = ComposeTheme.DARK) {
        RatingUserComposableScreen(
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
private fun RatingUserComposableScreenFailurePreview() {
    AdaptThemeFade(composeTheme = ComposeTheme.DARK) {
        RatingUserComposableScreen(
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
