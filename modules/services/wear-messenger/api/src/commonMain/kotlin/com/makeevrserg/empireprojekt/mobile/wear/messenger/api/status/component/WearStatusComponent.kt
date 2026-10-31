package com.makeevrserg.empireprojekt.mobile.wear.messenger.api.status.component

import kotlinx.coroutines.flow.StateFlow
import kotlin.time.Instant

interface WearStatusComponent {
    val mergedState: StateFlow<Model>

    data class Model(
        val loadingCount: Int,
        val successCount: Int,
        val failureCount: Int,
        val updatedAt: Instant?
    )
}
