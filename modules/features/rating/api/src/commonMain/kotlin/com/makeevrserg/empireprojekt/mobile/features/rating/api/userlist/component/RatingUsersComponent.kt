package com.makeevrserg.empireprojekt.mobile.features.rating.api.userlist.component

import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingUser
import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingUserId
import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingsFilter
import kotlinx.coroutines.flow.StateFlow

interface RatingUsersComponent {
    val model: StateFlow<Model>

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

    data class Model(
        val items: List<RatingUser>,
        val filter: RatingsFilter,
        val isLoading: Boolean,
        val isFailure: Boolean,
        val isLastPage: Boolean,
    )
}
