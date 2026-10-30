package com.makeevrserg.empireprojekt.mobile.core.ui.webview.composable

import android.webkit.WebView
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.makeevrserg.empireprojekt.mobile.core.ui.placeholder.composable.LoadingContent
import com.makeevrserg.empireprojekt.mobile.core.ui.webview.event.LoadingWebViewClient
import com.makeevrserg.empireprojekt.mobile.core.ui.webview.util.applyAstraSettings

@Composable
fun AstraWebView(
    url: String,
    modifier: Modifier = Modifier,
    onWebViewCreate: (WebView) -> Unit = {},
) {
    val webViewClient = remember { LoadingWebViewClient() }
    val isLoading by webViewClient.isLoading.collectAsState()
    Box(modifier = modifier) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                WebView(context).apply {
                    applyAstraSettings()
                    this.webViewClient = webViewClient
                    loadUrl(url)
                }.also(onWebViewCreate)
            }
        )
        Crossfade(
            targetState = isLoading,
            label = "web view loading indicator"
        ) { isPageLoading ->
            if (!isPageLoading) return@Crossfade
            LoadingContent(Modifier.fillMaxSize())
        }
    }
}
