package com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model

import com.makeevrserg.empireprojekt.mobile.services.core.model.LocalSortOrder
import kotlinx.serialization.Serializable

@Serializable
data class TownsFilter(
    val query: String = "",
    val publicType: TownPublicType = TownPublicType.NONE,
    val nameSort: LocalSortOrder = LocalSortOrder.NONE,
    val tagSort: LocalSortOrder = LocalSortOrder.NONE,
    val founderSort: LocalSortOrder = LocalSortOrder.NONE,
    val nationSort: LocalSortOrder = LocalSortOrder.NONE,
    val dateSort: LocalSortOrder = LocalSortOrder.NONE,
    val residentsSort: LocalSortOrder = LocalSortOrder.NONE
) {
    enum class TownPublicType {
        NONE, PUBLIC, PRIVATE
    }
}
