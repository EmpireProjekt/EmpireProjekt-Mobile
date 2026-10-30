@file:OptIn(ExperimentalTestApi::class)

package com.makeevrserg.empireprojekt.mobile.core.ui.paging.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTopPositionInRootIsEqualTo
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.resources.common_action_reload
import com.makeevrserg.empireprojekt.mobile.core.resources.common_paging_empty
import com.makeevrserg.empireprojekt.mobile.core.resources.common_paging_last_page_desc
import com.makeevrserg.empireprojekt.mobile.core.resources.common_paging_network_error
import com.makeevrserg.empireprojekt.mobile.core.ui.paging.model.PagingContent
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import dev.icerock.moko.resources.StringResource
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class PagingWidgetTest {
    private fun resolveString(resource: StringResource): String {
        return RuntimeEnvironment.getApplication().getString(resource.resourceId)
    }

    private fun createPaging(
        isEmpty: Boolean,
        isLastPage: Boolean,
        isFailure: Boolean
    ): PagingContent {
        return PagingContent(
            isEmpty = isEmpty,
            isLastPage = isLastPage,
            isLoading = false,
            isFailure = isFailure
        )
    }

    @Test
    fun GIVEN_item_WHEN_shimmer_loader_is_shown_THEN_a_full_page_of_spaced_placeholders_is_displayed() {
        runComposeUiTest {
            setContent {
                AdaptThemeFade {
                    PagingShimmerLoader {
                        Box(Modifier.testTag(ITEM_TAG).fillMaxWidth().height(10.dp))
                    }
                }
            }

            onAllNodesWithTag(ITEM_TAG).assertCountEquals(8)
            onAllNodesWithTag(ITEM_TAG)[1].assertTopPositionInRootIsEqualTo(18.dp)
        }
    }

    @Test
    fun GIVEN_last_page_with_items_WHEN_footer_is_shown_THEN_end_of_list_is_displayed() {
        runComposeUiTest {
            setContent {
                AdaptThemeFade {
                    PagingFooter(
                        paging = createPaging(
                            isEmpty = false,
                            isLastPage = true,
                            isFailure = false
                        ),
                        onReload = {},
                        loader = {}
                    )
                }
            }

            onNodeWithText(resolveString(MR.strings.common_paging_last_page_desc)).assertIsDisplayed()
        }
    }

    @Test
    fun GIVEN_last_page_without_items_WHEN_footer_is_shown_THEN_empty_list_is_displayed() {
        runComposeUiTest {
            setContent {
                AdaptThemeFade {
                    PagingFooter(
                        paging = createPaging(
                            isEmpty = true,
                            isLastPage = true,
                            isFailure = false
                        ),
                        onReload = {},
                        loader = {}
                    )
                }
            }

            onNodeWithText(resolveString(MR.strings.common_paging_empty)).assertIsDisplayed()
        }
    }

    @Test
    fun GIVEN_failed_page_WHEN_reload_is_clicked_THEN_reload_is_requested() {
        runComposeUiTest {
            var reloads = 0
            setContent {
                AdaptThemeFade {
                    PagingFooter(
                        paging = createPaging(
                            isEmpty = false,
                            isLastPage = false,
                            isFailure = true
                        ),
                        onReload = { reloads++ },
                        loader = {}
                    )
                }
            }

            onNodeWithText(resolveString(MR.strings.common_paging_network_error)).assertIsDisplayed()
            onNodeWithText(resolveString(MR.strings.common_action_reload)).performClick()
            assertEquals(1, reloads)
        }
    }

    @Test
    fun GIVEN_loading_page_WHEN_footer_is_shown_THEN_loader_is_displayed() {
        runComposeUiTest {
            setContent {
                AdaptThemeFade {
                    PagingFooter(
                        paging = PagingContent(
                            isEmpty = true,
                            isLastPage = false,
                            isLoading = true,
                            isFailure = false
                        ),
                        onReload = {},
                        loader = { Box(Modifier.testTag(ITEM_TAG)) }
                    )
                }
            }

            onNodeWithTag(ITEM_TAG).assertExists()
        }
    }

    companion object {
        private const val ITEM_TAG = "item"
    }
}
