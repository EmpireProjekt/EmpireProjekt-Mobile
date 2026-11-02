@file:OptIn(ExperimentalTestApi::class)

package com.makeevrserg.empireprojekt.mobile.core.ui.common.webview.composable

import android.webkit.WebView
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runComposeUiTest
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable.AdaptThemeFadeComposable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf

@RunWith(RobolectricTestRunner::class)
class AstraWebViewTest {
    @Test
    fun GIVEN_url_WHEN_web_view_is_shown_THEN_created_web_view_loads_it() {
        runComposeUiTest {
            val createdWebViews = mutableListOf<WebView>()
            setContent {
                AdaptThemeFadeComposable {
                    AstraWebViewComposable(
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
    }

    companion object {
        private const val PAGE_URL = "https://map.astrainteractive.ru"
    }
}
