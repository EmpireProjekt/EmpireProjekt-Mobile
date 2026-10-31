package com.makeevrserg.empireprojekt.mobile.features.rating.api.user.component

import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.Rating
import kotlinx.coroutines.flow.StateFlow

interface RatingUserComponent {
    val model: StateFlow<Model>

    fun reset()

    fun loadNextPage()

    data class Model(
        val items: List<Rating>,
        val reviewedUserName: String,
        val isLoading: Boolean,
        val isLastPage: Boolean,
        val isFailure: Boolean,
    )
}
