package com.makeevrserg.empireprojekt.mobile.features.status.impl.url.api

internal interface UrlStatusRepository {
    suspend fun isActive(): Result<Boolean>
}
