package com.makeevrserg.empireprojekt.mobile.features.root.screen

import com.makeevrserg.empireprojekt.mobile.services.core.PopComponent
import kotlinx.serialization.Serializable

interface RootRouter : PopComponent {
    fun push(configuration: Configuration)
    fun replaceCurrent(configuration: Configuration)
    fun replaceAll(configuration: Configuration)

    @Serializable
    sealed interface Configuration {
        @Serializable
        data object Splash : Configuration

        @Serializable
        data object Pager : Configuration

        @Serializable
        data object Towns : Configuration

        @Serializable
        data object RatingUsers : Configuration

        @Serializable
        data object Votes : Configuration

        @Serializable
        class RatingUser(val userId: Long, val userName: String) : Configuration
    }
}
