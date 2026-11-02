package com.makeevrserg.empireprojekt.mobile.features.rating.api.user.model

import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.Rating

data class RatingUserState(
    val items: List<Rating>,
    val reviewedUserName: String,
    val isLoading: Boolean,
    val isLastPage: Boolean,
    val isFailure: Boolean,
)
