package com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Town(
    val name: String,
    val mayor: String,
    val nation: String,
    val townBoard: String,
    val tag: String,
    val founder: String,
    val hasUpkeep: Boolean,
    val open: Boolean,
    val public: Boolean,
    val outlaws: List<String>,
    val registered: Long,
    val ruined: Boolean,
    val residentsCount: Long
)
