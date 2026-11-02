package com.makeevrserg.empireprojekt.mobile.core.ui.common.webview.util

import android.view.ViewGroup
import android.webkit.WebView

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
