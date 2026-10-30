@file:OptIn(ExperimentalTestApi::class)

package com.makeevrserg.empireprojekt.mobile.core.ui.paging.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import com.makeevrserg.empireprojekt.mobile.core.ui.paging.model.PagingContent
import com.makeevrserg.empireprojekt.mobile.core.ui.text.composable.AstraText
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PagingLazyColumnTest {
    private fun createPaging(isEmpty: Boolean, isLoading: Boolean): PagingContent {
        return PagingContent(
            isEmpty = isEmpty,
            isLastPage = false,
            isLoading = isLoading,
            isFailure = false
        )
    }

    @Test
    fun GIVEN_header_and_items_WHEN_list_is_shown_THEN_both_are_displayed() {
        runComposeUiTest {
            setContent {
                AdaptThemeFade {
                    PagingLazyColumn(onLoadNextPage = {}) {
                        item { AstraText(text = "Filter") }
                        items(listOf("RomaRoman", "cinnamonrein")) { name -> AstraText(text = name) }
                    }
                }
            }

            onNodeWithText("Filter").assertIsDisplayed()
            onNodeWithText("RomaRoman").assertIsDisplayed()
            onNodeWithText("cinnamonrein").assertIsDisplayed()
        }
    }

    @Test
    fun GIVEN_loading_page_WHEN_list_is_shown_THEN_a_page_of_shimmer_placeholders_is_displayed() {
        runComposeUiTest {
            setContent {
                AdaptThemeFade {
                    PagingLazyColumn(onLoadNextPage = {}) {
                        pagingFooter(
                            paging = createPaging(isEmpty = true, isLoading = true),
                            onReload = {},
                            shimmerItem = { Box(Modifier.testTag(SHIMMER_TAG)) }
                        )
                    }
                }
            }

            onAllNodesWithTag(SHIMMER_TAG).assertCountEquals(8)
        }
    }

    @Test
    fun GIVEN_short_list_WHEN_its_end_is_visible_THEN_next_page_is_requested() {
        runComposeUiTest {
            var requestedPages = 0
            setContent {
                AdaptThemeFade {
                    PagingLazyColumn(onLoadNextPage = { requestedPages++ }) {
                        item { AstraText(text = "RomaRoman") }
                    }
                }
            }
            waitForIdle()

            assertTrue(requestedPages > 0)
        }
    }

    companion object {
        private const val SHIMMER_TAG = "shimmer"
    }
}
