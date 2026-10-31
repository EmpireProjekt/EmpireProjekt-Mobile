package com.makeevrserg.empireprojekt.mobile.features.webview.impl.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.backhandler.BackCallback
import com.makeevrserg.empireprojekt.mobile.features.webview.api.component.WebViewComponent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.astrainteractive.klibs.mikro.extensions.arkivanov.coroutineScope

internal class DefaultWebViewComponent(
    componentContext: ComponentContext,
    private val onPopCallback: WebViewComponent.OnPopCallback,
    override val url: String,
) : WebViewComponent, ComponentContext by componentContext {

    private val _eventFlow = MutableSharedFlow<WebViewComponent.Event>()
    override val eventFlow: SharedFlow<WebViewComponent.Event> = _eventFlow.asSharedFlow()

    private val scope = coroutineScope()
    private val backCallback = BackCallback {
        scope.launch { _eventFlow.emit(WebViewComponent.Event.BackPressed) }
    }

    override fun onAction(action: WebViewComponent.Action) {
        when (action) {
            WebViewComponent.Action.CantGoBack -> onPopCallback.invoke()
        }
    }

    init {
        backHandler.register(backCallback)
    }
}
