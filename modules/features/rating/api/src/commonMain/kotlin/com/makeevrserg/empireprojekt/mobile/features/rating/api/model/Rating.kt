package com.makeevrserg.empireprojekt.mobile.features.rating.api.model

import kotlinx.serialization.Serializable

@Serializable
class Rating(
    val userCreatedReport: RatingUser?,
    val reportedUser: RatingUser,
    val rating: Int,
    val ratingTypeIndex: Int,
    val message: String,
    val time: Long
)
