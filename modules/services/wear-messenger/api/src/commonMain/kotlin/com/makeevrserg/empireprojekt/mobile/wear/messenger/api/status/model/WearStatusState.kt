package com.makeevrserg.empireprojekt.mobile.wear.messenger.api.status.model

import kotlin.time.Instant

data class WearStatusState(
    val loadingCount: Int,
    val successCount: Int,
    val failureCount: Int,
    val updatedAt: Instant?
)
