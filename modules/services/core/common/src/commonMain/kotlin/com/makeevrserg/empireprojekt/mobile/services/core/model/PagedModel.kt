package com.makeevrserg.empireprojekt.mobile.services.core.model

interface PagedModel<T> {
    val data: List<T>
    val total: Long
    val page: Int
}
