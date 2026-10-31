package com.makeevrserg.empireprojekt.mobile.features.status.impl.url.mvikotlin

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.api.UrlStatusRepository
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.api.UrlStatusStore.Action
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.api.UrlStatusStore.Intent
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.api.UrlStatusStore.Label
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.api.UrlStatusStore.Message
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.api.UrlStatusStore.State
import kotlinx.coroutines.launch

internal class UrlStatusExecutor(
    private val urlStatusRepository: UrlStatusRepository
) : CoroutineExecutor<Intent, Action, State, Message, Label>() {

    private suspend fun checkOnce(force: Boolean, state: State) {
        if (state == State.LOADING && !force) return
        Message.NowLoading.run(::dispatch)
        val response = urlStatusRepository.isActive().getOrNull() ?: false
        if (response) {
            Message.Available.run(::dispatch)
        } else {
            Message.UnAvailable.run(::dispatch)
        }
    }

    override fun executeIntent(intent: Intent) {
        when (intent) {
            is Intent.CheckOnce -> scope.launch {
                checkOnce(intent.force, state())
            }
        }
    }

    override fun executeAction(action: Action) {
        when (action) {
            is Action.CheckOnce -> scope.launch {
                checkOnce(action.force, state())
            }
        }
    }
}
