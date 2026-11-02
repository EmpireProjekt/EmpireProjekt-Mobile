package com.makeevrserg.empireprojekt.mobile.features.webview.api.component

import kotlinx.coroutines.flow.SharedFlow

interface WebViewComponent {
    val url: String
    val eventFlow: SharedFlow<Event>

    fun onAction(action: Action)

    fun interface OnPopCallback {
        operator fun invoke()
    }

    sealed interface Event {
        data object BackPressed : Event
    }

    sealed interface Action {
        data object CantGoBack : Action
    }
}
