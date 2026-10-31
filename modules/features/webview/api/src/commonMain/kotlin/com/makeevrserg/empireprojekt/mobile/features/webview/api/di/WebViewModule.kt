package com.makeevrserg.empireprojekt.mobile.features.webview.api.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.webview.api.component.WebViewComponent

interface WebViewModule {
    fun createWebViewComponent(
        componentContext: ComponentContext,
        url: String,
        onPopCallback: WebViewComponent.OnPopCallback
    ): WebViewComponent
}
