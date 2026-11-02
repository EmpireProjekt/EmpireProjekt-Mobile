package com.makeevrserg.empireprojekt.mobile.features.rating.api.userlist.model

import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingUser
import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingsFilter

data class RatingUsersState(
    val items: List<RatingUser>,
    val filter: RatingsFilter,
    val isLoading: Boolean,
    val isFailure: Boolean,
    val isLastPage: Boolean,
)
