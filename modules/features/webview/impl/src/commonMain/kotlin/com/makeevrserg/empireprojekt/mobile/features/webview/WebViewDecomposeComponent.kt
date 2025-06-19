package com.makeevrserg.empireprojekt.mobile.features.webview

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.backhandler.BackCallback
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.astrainteractive.klibs.mikro.extensions.arkivanov.CoroutineScopeExt.coroutineScope

class WebViewDecomposeComponent(
    componentContext: ComponentContext,
    private val onPop: () -> Unit,
    val url: String,
) : ComponentContext by componentContext {

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val scope = coroutineScope()
    private val backCallback = BackCallback {
        scope.launch { _eventFlow.emit(Event.BackPressed) }
    }

    fun onAction(action: Action) {
        when (action) {
            Action.CantGoBack -> onPop.invoke()
        }
    }

    init {
        backHandler.register(backCallback)
    }

    sealed interface Event {
        data object BackPressed : Event
    }

    sealed interface Action {
        data object CantGoBack : Action
    }
}
