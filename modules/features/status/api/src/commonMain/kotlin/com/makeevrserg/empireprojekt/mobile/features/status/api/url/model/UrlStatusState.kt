package com.makeevrserg.empireprojekt.mobile.features.status.api.url.model

import dev.icerock.moko.resources.desc.StringDesc

data class UrlStatusState(
    val title: StringDesc,
    val isLoading: Boolean,
    val status: LoadingStatus
) {
    enum class LoadingStatus {
        LOADING, SUCCESS, ERROR
    }
}
