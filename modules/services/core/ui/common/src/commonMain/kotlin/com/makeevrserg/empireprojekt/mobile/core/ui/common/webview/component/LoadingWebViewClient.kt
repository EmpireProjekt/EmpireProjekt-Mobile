package com.makeevrserg.empireprojekt.mobile.core.ui.common.webview.component

import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class LoadingWebViewClient : WebViewClient() {
    private val loadingState = MutableStateFlow(true)

    val isLoading: StateFlow<Boolean> = loadingState.asStateFlow()

    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        loadingState.value = true
        return super.shouldOverrideUrlLoading(view, url)
    }

    override fun onPageFinished(view: WebView, url: String) {
        loadingState.value = false
        super.onPageFinished(view, url)
    }
}
