package com.makeevrserg.empireprojekt.mobile.features.rating.api.userlist.component

import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingUserId
import com.makeevrserg.empireprojekt.mobile.features.rating.api.userlist.model.RatingUsersState
import kotlinx.coroutines.flow.StateFlow

interface RatingUsersComponent {
    val model: StateFlow<RatingUsersState>

    fun reset()

    fun loadNextPage()

    fun updateQuery(query: String)

    fun nextLastUpdateSort()

    fun nextRatingSort()

    fun nextNameSort()

    fun showUserRatings(id: RatingUserId, userName: String)

    fun interface OnShowUserRatingsCallback {
        operator fun invoke(userId: RatingUserId, userName: String)
    }
}
