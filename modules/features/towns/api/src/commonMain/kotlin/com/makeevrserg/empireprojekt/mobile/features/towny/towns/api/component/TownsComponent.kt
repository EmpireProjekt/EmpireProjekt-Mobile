package com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.component

import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.TownsState
import kotlinx.coroutines.flow.StateFlow

interface TownsComponent {
    val model: StateFlow<TownsState>

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
}
