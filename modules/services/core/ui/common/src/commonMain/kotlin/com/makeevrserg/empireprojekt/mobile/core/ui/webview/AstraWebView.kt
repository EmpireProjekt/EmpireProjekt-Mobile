package com.makeevrserg.empireprojekt.mobile.core.ui.webview

import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.makeevrserg.empireprojekt.mobile.core.ui.placeholder.AstraLoading
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

internal fun WebView.applyAstraSettings() {
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
    settings.loadWithOverviewMode = true
    settings.domStorageEnabled = true
    settings.blockNetworkLoads = false
    settings.blockNetworkImage = false
    settings.databaseEnabled = true
    settings.setSupportZoom(true)
    scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
    isScrollbarFadingEnabled = false
}

/**
 * Zoomable, JavaScript-enabled [WebView] showing [url] with a loading indicator on top
 * until each page finishes loading.
 *
 * @param onWebViewCreate receives the created [WebView], e.g. to drive its back navigation
 */
@Composable
fun AstraWebView(
    url: String,
    modifier: Modifier = Modifier,
    onWebViewCreate: (WebView) -> Unit = {},
) {
    var isLoading by remember { mutableStateOf(true) }
    Box(modifier = modifier) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                WebView(context).apply {
                    applyAstraSettings()
                    webViewClient = LoadingWebViewClient { isPageLoading -> isLoading = isPageLoading }
                    loadUrl(url)
                }.also(onWebViewCreate)
            }
        )
        Crossfade(
            targetState = isLoading,
            label = "web view loading indicator"
        ) { isPageLoading ->
            if (isPageLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    AstraLoading(size = AppTheme.dimens.M)
                }
            }
        }
    }
}
