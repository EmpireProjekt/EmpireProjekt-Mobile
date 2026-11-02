package com.makeevrserg.empireprojekt.mobile.features.rating.api.model

import kotlinx.serialization.Serializable

@Serializable
data class RatingUser(
    val id: Long,
    val minecraftUUID: String,
    val minecraftName: String,
    val lastUpdated: Long,
    val totalRating: Long,
    val ratingVotes: Long,
)
