package com.makeevrserg.empireprojekt.mobile.features.ui.rating.users

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.makeevrserg.empireprojekt.mobile.features.rating.users.presentation.RatingUsersComponent
import com.makeevrserg.empireprojekt.mobile.services.core.PopComponent
import kotlinx.coroutines.Dispatchers

@Composable
fun RatingUsersScreenComponent(
    popComponent: PopComponent,
    ratingUsersComponent: RatingUsersComponent,
    modifier: Modifier = Modifier
) {
    val model by ratingUsersComponent.model.collectAsState(Dispatchers.Main.immediate)
    val popModel by popComponent.popModel.subscribeAsState()
    RatingUsersComposableScreen(
        model = model,
        onBack = popModel.popActionOrNull,
        onUpdateQuery = ratingUsersComponent::updateQuery,
        onNameSortClick = ratingUsersComponent::nextNameSort,
        onLastUpdateSortClick = ratingUsersComponent::nextLastUpdateSort,
        onRatingSortClick = ratingUsersComponent::nextRatingSort,
        onUserClick = { ratingUserModel ->
            ratingUsersComponent.showUserRatings(
                ratingUserModel.id,
                ratingUserModel.minecraftName
            )
        },
        onLoadNextPage = ratingUsersComponent::loadNextPage,
        onReset = ratingUsersComponent::reset,
        modifier = modifier
    )
}
