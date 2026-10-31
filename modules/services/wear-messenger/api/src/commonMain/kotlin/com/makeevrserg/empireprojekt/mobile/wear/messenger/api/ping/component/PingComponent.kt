package com.makeevrserg.empireprojekt.mobile.wear.messenger.api.ping.component

import kotlinx.coroutines.flow.StateFlow
import kotlin.time.Instant

interface PingComponent {
    val model: StateFlow<Model>

    sealed interface Model {
        data class Success(val updatedAt: Instant) : Model

        data class NoConnection(val updatedAt: Instant) : Model
    }
}
