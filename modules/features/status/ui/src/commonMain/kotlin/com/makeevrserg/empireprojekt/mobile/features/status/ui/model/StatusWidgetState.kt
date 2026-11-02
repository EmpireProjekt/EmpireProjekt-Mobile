package com.makeevrserg.empireprojekt.mobile.features.status.ui.model

import com.makeevrserg.empireprojekt.mobile.features.status.api.url.model.UrlStatusState

internal data class StatusWidgetState(
    val status: UrlStatusState.LoadingStatus,
    val isLoading: Boolean
)
