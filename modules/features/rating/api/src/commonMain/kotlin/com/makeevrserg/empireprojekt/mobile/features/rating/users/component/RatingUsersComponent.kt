package com.makeevrserg.empireprojekt.mobile.features.rating.users.component

import com.makeevrserg.empireprojekt.mobile.features.rating.model.RatingUserId
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.empireapi.models.rating.RatingUserModel
import ru.astrainteractive.empireapi.models.rating.RatingsFilterModel

interface RatingUsersComponent {
    val model: StateFlow<Model>

    fun reset()

    fun loadNextPage()

    fun updateQuery(query: String)

    fun nextLastUpdateSort()

    fun nextRatingSort()

    fun nextNameSort()

    fun showUserRatings(id: RatingUserId, userName: String)

    data class Model(
        val items: List<RatingUserModel> = emptyList(),
        val filter: RatingsFilterModel = RatingsFilterModel(),
        val isLoading: Boolean = false,
        val isFailure: Boolean = false,
        val isLastPage: Boolean = false,
    )
}
