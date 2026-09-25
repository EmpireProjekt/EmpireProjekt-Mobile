@file:Suppress("FunctionNaming", "MagicNumber")
@file:OptIn(ExperimentalTestApi::class)

package com.makeevrserg.empireprojekt.mobile.core.ui.paging

import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import com.makeevrserg.empireprojekt.mobile.core.ui.text.AstraText
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PagingLazyColumnTest {
    @Test
    fun GIVEN_header_and_items_WHEN_list_is_shown_THEN_both_are_displayed() = runComposeUiTest {
        setContent {
            AdaptThemeFade {
                PagingLazyColumn(
                    items = listOf("RomaRoman", "cinnamonrein"),
                    isLastPage = false,
                    isLoading = false,
                    isFailure = false,
                    onLoadNextPage = {},
                    onReload = {},
                    shimmerItem = {},
                    header = { AstraText(text = "Filter") },
                    itemContent = { name -> AstraText(text = name) }
                )
            }
        }

        onNodeWithText("Filter").assertIsDisplayed()
        onNodeWithText("RomaRoman").assertIsDisplayed()
        onNodeWithText("cinnamonrein").assertIsDisplayed()
    }

    @Test
    fun GIVEN_loading_page_WHEN_list_is_shown_THEN_a_page_of_shimmer_placeholders_is_displayed() = runComposeUiTest {
        setContent {
            AdaptThemeFade {
                PagingLazyColumn(
                    items = emptyList<String>(),
                    isLastPage = false,
                    isLoading = true,
                    isFailure = false,
                    onLoadNextPage = {},
                    onReload = {},
                    shimmerItem = { Box(Modifier.testTag(SHIMMER_TAG)) },
                    itemContent = { name -> AstraText(text = name) }
                )
            }
        }

        onAllNodesWithTag(SHIMMER_TAG).assertCountEquals(8)
    }

    @Test
    fun GIVEN_short_list_WHEN_its_end_is_visible_THEN_next_page_is_requested() = runComposeUiTest {
        var requestedPages = 0
        setContent {
            AdaptThemeFade {
                PagingLazyColumn(
                    items = listOf("RomaRoman"),
                    isLastPage = false,
                    isLoading = false,
                    isFailure = false,
                    onLoadNextPage = { requestedPages++ },
                    onReload = {},
                    shimmerItem = {},
                    itemContent = { name -> AstraText(text = name) }
                )
            }
        }
        waitForIdle()

        assertTrue(requestedPages > 0)
    }

    companion object {
        private const val SHIMMER_TAG = "shimmer"
    }
}
