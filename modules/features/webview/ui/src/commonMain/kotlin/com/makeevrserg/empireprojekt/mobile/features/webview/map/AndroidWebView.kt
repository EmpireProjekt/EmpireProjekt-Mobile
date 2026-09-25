package com.makeevrserg.empireprojekt.mobile.features.webview.map

import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.webview.AstraWebView
import com.makeevrserg.empireprojekt.mobile.features.webview.WebViewDecomposeComponent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun AndroidWebView(
    webViewDecomposeComponent: WebViewDecomposeComponent,
    modifier: Modifier = Modifier
) {
    var webView by remember { mutableStateOf<WebView?>(null) }
    LaunchedEffect(webViewDecomposeComponent) {
        webViewDecomposeComponent.eventFlow
            .onEach { event ->
                when (event) {
                    WebViewDecomposeComponent.Event.BackPressed -> {
                        if (webView?.canGoBack() == false) {
                            val action = WebViewDecomposeComponent.Action.CantGoBack
                            webViewDecomposeComponent.onAction(action)
                        } else {
                            webView?.goBack()
                        }
                    }
                }
            }
            .collect()
    }
    AstraWebView(
        url = webViewDecomposeComponent.url,
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.astraColors.surface.primaryVariant)
            .navigationBarsPadding()
            .systemBarsPadding(),
        onWebViewCreate = { createdWebView -> webView = createdWebView }
    )
}
