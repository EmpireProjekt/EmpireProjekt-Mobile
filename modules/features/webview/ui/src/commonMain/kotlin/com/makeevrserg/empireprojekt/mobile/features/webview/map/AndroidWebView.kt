package com.makeevrserg.empireprojekt.mobile.features.webview.map

import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.makeevrserg.empireprojekt.mobile.core.ui.placeholder.AstraLoading
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.webview.WebViewDecomposeComponent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Suppress("LongMethod")
@Composable
fun AndroidWebView(
    webViewDecomposeComponent: WebViewDecomposeComponent,
) {
    var webView: WebView? = remember {
        null
    }
    LaunchedEffect(webViewDecomposeComponent) {
        webViewDecomposeComponent.eventFlow
            .onEach {
                when (it) {
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
    var isLoading by remember {
        mutableStateOf(true)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.astraColors.surface.primaryVariant)
            .navigationBarsPadding()
            .systemBarsPadding()
    ) {
        AndroidView(
            modifier = Modifier.weight(1f),
            factory = { context ->
                webView ?: WebView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    settings.javaScriptEnabled = true
                    settings.useWideViewPort = true
                    settings.allowFileAccess = true
                    settings.allowContentAccess = true
                    settings.builtInZoomControls = true
                    settings.displayZoomControls = false
                    settings.useWideViewPort = true
                    settings.loadWithOverviewMode = true
                    settings.domStorageEnabled = true
                    settings.blockNetworkLoads = false
                    settings.blockNetworkImage = false
                    settings.databaseEnabled = true
                    settings.setSupportZoom(true)
                    scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
                    isScrollbarFadingEnabled = false
                    webViewClient = LoadingWebViewClient { isLoading = it }
                    loadUrl(webViewDecomposeComponent.url)
                }.also { webView = it }
            },
            update = {
                webView = it
            }
        )
    }
    Crossfade(
        targetState = isLoading,
        label = "loading indicator crossfade"
    ) { localIsLoading ->
        if (localIsLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                AstraLoading(AppTheme.dimens.M)
            }
        }
    }
}
