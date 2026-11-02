package com.makeevrserg.empireprojekt.mobile.features.status.api.url.component

import com.makeevrserg.empireprojekt.mobile.features.status.api.url.model.UrlStatusState
import kotlinx.coroutines.flow.StateFlow

interface UrlStatusComponent {
    val model: StateFlow<UrlStatusState>
    fun checkStatus()
}
