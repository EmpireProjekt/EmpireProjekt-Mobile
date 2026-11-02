package com.makeevrserg.empireprojekt.mobile.features.status.impl.url.api

import com.arkivanov.mvikotlin.core.store.Store

internal interface UrlStatusStore : Store<UrlStatusStore.Intent, UrlStatusStore.State, UrlStatusStore.Label> {
    enum class State {
        LOADING, SUCCESS, ERROR
    }

    sealed interface Intent {
        class CheckOnce(val force: Boolean) : Intent
    }

    sealed interface Message {
        data object NowLoading : Message
        data object Available : Message
        data object UnAvailable : Message
    }

    sealed interface Label

    sealed interface Action {
        class CheckOnce(val force: Boolean) : Action
    }
}
