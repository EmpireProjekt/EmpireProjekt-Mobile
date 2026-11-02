package com.makeevrserg.empireprojekt.mobile.features.webview.ui.map.composable

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
import com.makeevrserg.empireprojekt.mobile.core.ui.common.webview.composable.AstraWebViewComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.webview.api.component.WebViewComponent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

private fun popOrGoBack(webView: WebView?, webViewComponent: WebViewComponent) {
    if (webView?.canGoBack() == false) {
        webViewComponent.onAction(WebViewComponent.Action.CantGoBack)
    } else {
        webView?.goBack()
    }
}

@Composable
fun AndroidWebViewComposable(
    webViewComponent: WebViewComponent,
    modifier: Modifier = Modifier
) {
    var webView by remember { mutableStateOf<WebView?>(null) }
    LaunchedEffect(webViewComponent) {
        webViewComponent.eventFlow
            .onEach { event ->
                when (event) {
                    WebViewComponent.Event.BackPressed -> popOrGoBack(webView, webViewComponent)
                }
            }
            .collect()
    }
    AstraWebViewComposable(
        url = webViewComponent.url,
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.astraColors.surface.primaryVariant)
            .navigationBarsPadding()
            .systemBarsPadding(),
        onWebViewCreate = { createdWebView -> webView = createdWebView }
    )
}
