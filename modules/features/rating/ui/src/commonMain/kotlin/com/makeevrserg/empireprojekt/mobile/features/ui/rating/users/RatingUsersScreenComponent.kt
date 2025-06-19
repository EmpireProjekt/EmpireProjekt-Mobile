package com.makeevrserg.empireprojekt.mobile.features.ui.rating.users

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.core.ui.paging.OnEndReached
import com.makeevrserg.empireprojekt.mobile.core.ui.paging.PagingWidget
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.rating.users.presentation.RatingUsersComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.components.RatingUserShimmerWidget
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.components.RatingUserWidget
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.components.RatingUsersAppBar
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.components.RatingsFilterCard
import com.makeevrserg.empireprojekt.mobile.services.core.PopComponent
import kotlinx.coroutines.Dispatchers

@Suppress("LongMethod")
@Composable
fun RatingUsersScreenComponent(
    popComponent: PopComponent,
    ratingUsersComponent: RatingUsersComponent
) {
    val model by ratingUsersComponent.model.collectAsState(Dispatchers.Main.immediate)
    val lazyListState = rememberLazyListState()
    lazyListState.OnEndReached {
        ratingUsersComponent.loadNextPage()
    }

    Scaffold(
        modifier = Modifier.animateContentSize(),
        topBar = {
            RatingUsersAppBar(
                popComponent = popComponent,
                model = model,
                onUpdateQuery = ratingUsersComponent::updateQuery
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = AppTheme.dimens.XS),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS),
            contentPadding = it,
            state = lazyListState
        ) {
            item {
                RatingsFilterCard(
                    filter = model.filter,
                    onNameSortClicked = ratingUsersComponent::nextNameSort,
                    onLastUpdateSortClicked = ratingUsersComponent::nextLastUpdateSort,
                    onRatingSortClicked = ratingUsersComponent::nextRatingSort
                )
            }
            items(model.items) { ratingUserModel ->
                RatingUserWidget(
                    model = ratingUserModel,
                    onClick = {
                        ratingUsersComponent.showUserRatings(
                            ratingUserModel.id,
                            ratingUserModel.minecraftName
                        )
                    }
                )
            }

            item {
                PagingWidget.Auto(
                    list = model.items,
                    isLastPage = model.isLastPage,
                    isLoading = model.isLoading,
                    isFailure = model.isFailure,
                    onReload = { ratingUsersComponent.reset() },
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
        }
    }
}
