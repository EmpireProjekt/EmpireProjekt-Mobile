package com.makeevrserg.empireprojekt.mobile.features.status.impl.url.mvikotlin

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.api.UrlStatusStore.Action
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

internal class UrlStatusBootstrapper : CoroutineBootstrapper<Action>() {
    private fun startPeriodicCheck() {
        scope.launch {
            while (isActive) {
                Action.CheckOnce(force = true).run(::dispatch)
                delay(DELAY)
            }
        }
    }

    override fun invoke() {
        startPeriodicCheck()
    }

    companion object {
        private val DELAY = 30.seconds
    }
}
