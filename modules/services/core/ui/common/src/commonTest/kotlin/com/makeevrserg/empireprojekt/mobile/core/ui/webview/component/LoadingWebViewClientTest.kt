package com.makeevrserg.empireprojekt.mobile.core.ui.webview.component

import android.webkit.WebView
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class LoadingWebViewClientTest {
    private fun createWebView(): WebView = WebView(RuntimeEnvironment.getApplication())

    @Test
    fun GIVEN_new_client_WHEN_created_THEN_page_is_loading() {
        val client = LoadingWebViewClient()

        assertTrue(client.isLoading.value)
    }

    @Test
    fun GIVEN_loading_page_WHEN_page_is_finished_THEN_loading_ends() {
        val client = LoadingWebViewClient()

        client.onPageFinished(createWebView(), PAGE_URL)

        assertFalse(client.isLoading.value)
    }

    @Test
    fun GIVEN_finished_page_WHEN_user_navigates_THEN_loading_starts_again() {
        val client = LoadingWebViewClient()
        val webView = createWebView()

        client.onPageFinished(webView, PAGE_URL)
        client.shouldOverrideUrlLoading(webView, OTHER_PAGE_URL)

        assertTrue(client.isLoading.value)
    }

    companion object {
        private const val PAGE_URL = "https://map.astrainteractive.ru"
        private const val OTHER_PAGE_URL = "https://map.astrainteractive.ru/world"
    }
}
