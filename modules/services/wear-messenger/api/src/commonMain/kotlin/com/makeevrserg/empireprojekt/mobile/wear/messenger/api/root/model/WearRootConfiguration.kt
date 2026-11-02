package com.makeevrserg.empireprojekt.mobile.wear.messenger.api.root.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface WearRootConfiguration {
    @Serializable
    data object Main : WearRootConfiguration

    @Serializable
    data object Statuses : WearRootConfiguration

    @Serializable
    data object Ping : WearRootConfiguration
}
