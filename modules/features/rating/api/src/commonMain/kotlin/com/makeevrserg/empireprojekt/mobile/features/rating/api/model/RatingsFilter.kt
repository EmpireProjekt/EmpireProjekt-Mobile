package com.makeevrserg.empireprojekt.mobile.features.rating.api.model

import com.makeevrserg.empireprojekt.mobile.services.core.model.LocalSortOrder
import kotlinx.serialization.Serializable

@Serializable
data class RatingsFilter(
    val nameSort: LocalSortOrder = LocalSortOrder.NONE,
    val lastUpdatedSort: LocalSortOrder = LocalSortOrder.NONE,
    val ratingSort: LocalSortOrder = LocalSortOrder.NONE,
    val query: String = "",
)
