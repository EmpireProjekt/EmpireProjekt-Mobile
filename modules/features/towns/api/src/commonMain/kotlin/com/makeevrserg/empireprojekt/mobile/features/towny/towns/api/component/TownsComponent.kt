package com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.component

import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.Town
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.TownsFilter
import kotlinx.coroutines.flow.StateFlow

interface TownsComponent {
    val model: StateFlow<Model>

    fun reset()

    fun loadNextPage()

    fun updateQuery(query: String)

    fun nextPublicType()

    fun nextNameSort()

    fun nextTagSort()

    fun nextFounderSort()

    fun nextNationSort()

    fun nextDateSort()

    fun nextResidentsSort()

    data class Model(
        val items: List<Town> = emptyList(),
        val filter: TownsFilter,
        val isLoading: Boolean = false,
        val isFailure: Boolean = false,
        val isLastPage: Boolean = false
    )
}
