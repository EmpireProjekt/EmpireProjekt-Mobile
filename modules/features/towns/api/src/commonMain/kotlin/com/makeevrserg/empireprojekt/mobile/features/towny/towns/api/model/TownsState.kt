package com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model

data class TownsState(
    val items: List<Town>,
    val filter: TownsFilter,
    val isLoading: Boolean,
    val isFailure: Boolean,
    val isLastPage: Boolean
)
