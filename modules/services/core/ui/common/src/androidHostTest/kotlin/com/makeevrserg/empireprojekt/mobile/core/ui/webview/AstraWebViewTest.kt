@file:Suppress("FunctionNaming")
@file:OptIn(ExperimentalTestApi::class)

package com.makeevrserg.empireprojekt.mobile.core.ui.webview

import android.webkit.WebView
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runComposeUiTest
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf

@RunWith(RobolectricTestRunner::class)
class AstraWebViewTest {
    private fun createWebView(): WebView = WebView(RuntimeEnvironment.getApplication())

    @Test
    fun GIVEN_new_client_WHEN_created_THEN_loading_is_reported() {
        val loadingStates = mutableListOf<Boolean>()

        LoadingWebViewClient { isLoading -> loadingStates += isLoading }

        assertEquals(listOf(true), loadingStates)
    }

    @Test
    fun GIVEN_loading_page_WHEN_page_is_finished_THEN_loading_ends() {
        val loadingStates = mutableListOf<Boolean>()
        val client = LoadingWebViewClient { isLoading -> loadingStates += isLoading }

        client.onPageFinished(createWebView(), PAGE_URL)

        assertEquals(listOf(true, false), loadingStates)
    }

    @Test
    fun GIVEN_finished_page_WHEN_user_navigates_THEN_loading_starts_again() {
        val loadingStates = mutableListOf<Boolean>()
        val client = LoadingWebViewClient { isLoading -> loadingStates += isLoading }
        val webView = createWebView()

        client.onPageFinished(webView, PAGE_URL)
        client.shouldOverrideUrlLoading(webView, OTHER_PAGE_URL)

        assertEquals(listOf(true, false, true), loadingStates)
    }

    @Test
    fun GIVEN_web_view_WHEN_design_system_settings_are_applied_THEN_scripts_storage_and_zoom_are_enabled() {
        val webView = createWebView()

        webView.applyAstraSettings()

        assertTrue(webView.settings.javaScriptEnabled)
        assertTrue(webView.settings.domStorageEnabled)
        assertTrue(webView.settings.builtInZoomControls)
        assertFalse(webView.settings.displayZoomControls)
        assertFalse(webView.isScrollbarFadingEnabled)
    }

    @Test
    fun GIVEN_url_WHEN_web_view_is_shown_THEN_created_web_view_loads_it() = runComposeUiTest {
        val createdWebViews = mutableListOf<WebView>()
        setContent {
            AdaptThemeFade {
                AstraWebView(
                    url = PAGE_URL,
                    onWebViewCreate = { webView -> createdWebViews += webView }
                )
            }
        }
        waitForIdle()

        val webView = createdWebViews.single()
        assertEquals(PAGE_URL, shadowOf(webView).lastLoadedUrl)
        assertTrue(webView.settings.javaScriptEnabled)
    }

    companion object {
        private const val PAGE_URL = "https://map.astrainteractive.ru"
        private const val OTHER_PAGE_URL = "https://map.astrainteractive.ru/world"
    }
}
