package com.makeevrserg.empireprojekt.mobile.core.ui.common.webview.util

import android.webkit.WebView
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class WebViewExtTest {
    @Test
    fun GIVEN_web_view_WHEN_design_system_settings_are_applied_THEN_scripts_storage_and_zoom_are_enabled() {
        val webView = WebView(RuntimeEnvironment.getApplication())

        webView.applyAstraSettings()

        assertTrue(webView.settings.javaScriptEnabled)
        assertTrue(webView.settings.domStorageEnabled)
        assertTrue(webView.settings.builtInZoomControls)
        assertFalse(webView.settings.displayZoomControls)
        assertFalse(webView.isScrollbarFadingEnabled)
    }
}
