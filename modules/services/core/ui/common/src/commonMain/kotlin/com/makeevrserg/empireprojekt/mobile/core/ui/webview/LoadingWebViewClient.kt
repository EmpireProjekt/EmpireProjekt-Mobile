package com.makeevrserg.empireprojekt.mobile.core.ui.webview

import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * Reports `true` from creation and on every navigation, `false` once a page has finished loading.
 */
internal class LoadingWebViewClient(
    private val onLoading: (Boolean) -> Unit
) : WebViewClient() {

    init {
        onLoading.invoke(true)
    }

    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        onLoading.invoke(true)
        return super.shouldOverrideUrlLoading(view, url)
    }

    override fun onPageFinished(view: WebView, url: String) {
        onLoading.invoke(false)
        return super.onPageFinished(view, url)
    }
}
