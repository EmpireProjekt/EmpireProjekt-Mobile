package com.makeevrserg.empireprojekt.mobile.services.core.model

import kotlinx.serialization.Serializable

@Serializable
class PagedData<T>(
    val data: List<T>,
    val total: Long,
    val page: Int
)
