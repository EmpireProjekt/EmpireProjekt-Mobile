package com.makeevrserg.empireprojekt.mobile.features.webview.impl.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.webview.api.component.WebViewComponent
import com.makeevrserg.empireprojekt.mobile.features.webview.api.di.WebViewModule
import com.makeevrserg.empireprojekt.mobile.features.webview.impl.component.DefaultWebViewComponent

class DefaultWebViewModule : WebViewModule {
    override fun createWebViewComponent(
        componentContext: ComponentContext,
        url: String,
        onPopCallback: WebViewComponent.OnPopCallback
    ): WebViewComponent {
        return DefaultWebViewComponent(
            componentContext = componentContext,
            onPopCallback = onPopCallback,
            url = url
        )
    }
}
