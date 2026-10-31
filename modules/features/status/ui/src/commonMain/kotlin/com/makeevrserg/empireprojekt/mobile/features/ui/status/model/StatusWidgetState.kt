package com.makeevrserg.empireprojekt.mobile.features.ui.status.model

import com.makeevrserg.empireprojekt.mobile.features.status.api.url.component.UrlStatusComponent

internal data class StatusWidgetState(
    val status: UrlStatusComponent.LoadingStatus,
    val isLoading: Boolean
)
