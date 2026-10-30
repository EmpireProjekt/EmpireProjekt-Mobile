package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.model

import kotlinx.serialization.Serializable

@Serializable
data class Status(
    val title: String,
    val isLoading: Boolean,
    val status: LoadingStatus
) {
    enum class LoadingStatus {
        LOADING, SUCCESS, ERROR
    }
}
