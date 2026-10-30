package com.makeevrserg.empireprojekt.mobile.core.ui.paging.model

data class PagingContent(
    val isEmpty: Boolean,
    val isLastPage: Boolean,
    val isLoading: Boolean,
    val isFailure: Boolean
)
