package ru.astrainteractive.empireapi.models.rating

import kotlinx.serialization.Serializable

@Serializable
data class UserRatingsRequest(
    val id: Long,
)
