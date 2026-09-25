@file:Suppress("FunctionNaming", "MagicNumber")
@file:OptIn(ExperimentalTestApi::class)

package com.makeevrserg.empireprojekt.mobile.core.ui.placeholder

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertLeftPositionInRootIsEqualTo
import androidx.compose.ui.test.assertTopPositionInRootIsEqualTo
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.paging.PagingWidget
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ShimmerCardTest {
    @Test
    fun GIVEN_width_fraction_WHEN_line_is_shown_THEN_it_takes_that_share_of_the_width() = runComposeUiTest {
        setContent {
            AdaptThemeFade {
                Box(Modifier.width(200.dp)) {
                    ShimmerLine(widthFraction = 0.4f, modifier = Modifier.testTag(LINE_TAG))
                }
            }
        }

        onNodeWithTag(LINE_TAG)
            .assertWidthIsEqualTo(80.dp)
            .assertHeightIsEqualTo(16.dp)
    }

    @Test
    fun GIVEN_card_WHEN_lines_are_placed_inside_THEN_they_are_inset_and_spaced() = runComposeUiTest {
        setContent {
            AdaptThemeFade {
                Box(Modifier.width(200.dp)) {
                    ShimmerCard {
                        ShimmerLine(widthFraction = 1f, modifier = Modifier.testTag(FIRST_LINE_TAG))
                        ShimmerLine(widthFraction = 1f, modifier = Modifier.testTag(SECOND_LINE_TAG))
                    }
                }
            }
        }

        onNodeWithTag(FIRST_LINE_TAG)
            .assertLeftPositionInRootIsEqualTo(16.dp)
            .assertTopPositionInRootIsEqualTo(16.dp)
            .assertWidthIsEqualTo(168.dp)
        onNodeWithTag(SECOND_LINE_TAG).assertTopPositionInRootIsEqualTo(40.dp)
    }

    @Test
    fun GIVEN_item_WHEN_shimmer_loader_is_shown_THEN_a_full_page_of_spaced_placeholders_is_displayed() =
        runComposeUiTest {
            setContent {
                AdaptThemeFade {
                    PagingWidget.ShimmerLoader {
                        Box(Modifier.testTag(ITEM_TAG).fillMaxWidth().height(10.dp))
                    }
                }
            }

            onAllNodesWithTag(ITEM_TAG).assertCountEquals(8)
            onAllNodesWithTag(ITEM_TAG)[1].assertTopPositionInRootIsEqualTo(18.dp)
        }

    companion object {
        private const val LINE_TAG = "line"
        private const val FIRST_LINE_TAG = "first line"
        private const val SECOND_LINE_TAG = "second line"
        private const val ITEM_TAG = "item"
    }
}
