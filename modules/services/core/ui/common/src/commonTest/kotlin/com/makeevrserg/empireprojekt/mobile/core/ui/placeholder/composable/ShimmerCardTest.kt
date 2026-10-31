@file:OptIn(ExperimentalTestApi::class)

package com.makeevrserg.empireprojekt.mobile.core.ui.placeholder.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertLeftPositionInRootIsEqualTo
import androidx.compose.ui.test.assertTopPositionInRootIsEqualTo
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.composable.AdaptThemeFadeComposable
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ShimmerCardTest {
    @Test
    fun GIVEN_width_fraction_WHEN_line_is_shown_THEN_it_takes_that_share_of_the_width() {
        runComposeUiTest {
            setContent {
                AdaptThemeFadeComposable {
                    Box(Modifier.width(200.dp)) {
                        ShimmerLineComposable(widthFraction = 0.4f, modifier = Modifier.testTag(LINE_TAG))
                    }
                }
            }

            onNodeWithTag(LINE_TAG)
                .assertWidthIsEqualTo(80.dp)
                .assertHeightIsEqualTo(16.dp)
        }
    }

    @Test
    fun GIVEN_card_WHEN_lines_are_placed_inside_THEN_they_are_inset_and_spaced() {
        runComposeUiTest {
            setContent {
                AdaptThemeFadeComposable {
                    Box(Modifier.width(200.dp)) {
                        ShimmerCardComposable {
                            ShimmerLineComposable(widthFraction = 1f, modifier = Modifier.testTag(FIRST_LINE_TAG))
                            ShimmerLineComposable(widthFraction = 1f, modifier = Modifier.testTag(SECOND_LINE_TAG))
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
    }

    companion object {
        private const val LINE_TAG = "line"
        private const val FIRST_LINE_TAG = "first line"
        private const val SECOND_LINE_TAG = "second line"
    }
}
