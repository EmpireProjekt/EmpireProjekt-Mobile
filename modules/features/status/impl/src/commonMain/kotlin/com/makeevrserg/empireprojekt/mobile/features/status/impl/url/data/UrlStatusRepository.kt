package com.makeevrserg.empireprojekt.mobile.features.status.impl.url.data

internal interface UrlStatusRepository {
    suspend fun isActive(): Result<Boolean>
}
