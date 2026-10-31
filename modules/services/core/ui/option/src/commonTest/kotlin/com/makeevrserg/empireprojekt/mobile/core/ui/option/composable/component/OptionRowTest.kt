@file:OptIn(ExperimentalTestApi::class)

package com.makeevrserg.empireprojekt.mobile.core.ui.option.composable.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import com.makeevrserg.empireprojekt.mobile.core.ui.composable.AdaptThemeFadeComposable
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class OptionRowTest {
    @Test
    fun GIVEN_info_row_WHEN_shown_THEN_title_info_and_value_are_displayed() {
        runComposeUiTest {
            setContent {
                AdaptThemeFadeComposable {
                    OptionInfoComposable(
                        text = "Version",
                        infoText = "Build number",
                        endText = "42",
                        icon = rememberVectorPainter(Icons.Filled.Image)
                    )
                }
            }

            onNodeWithText("Version").assertIsDisplayed()
            onNodeWithText("Build number").assertIsDisplayed()
            onNodeWithText("42").assertIsDisplayed()
        }
    }

    @Test
    fun GIVEN_row_without_info_WHEN_shown_THEN_only_the_title_is_displayed() {
        runComposeUiTest {
            setContent {
                AdaptThemeFadeComposable {
                    OptionRowComposable(text = "Ratings", end = {})
                }
            }

            onNodeWithText("Ratings").assertIsDisplayed()
            onNodeWithText("Build number").assertDoesNotExist()
        }
    }

    @Test
    fun GIVEN_link_row_WHEN_title_is_clicked_THEN_link_is_opened() {
        runComposeUiTest {
            var clicks = 0
            setContent {
                AdaptThemeFadeComposable {
                    OptionHrefComposable(
                        text = "Votes",
                        endText = "20m",
                        onClick = { clicks++ }
                    )
                }
            }

            onNodeWithText("Votes").performClick()

            assertEquals(1, clicks)
            onNodeWithText("20m").assertIsDisplayed()
        }
    }

    @Test
    fun GIVEN_clickable_option_WHEN_clicked_THEN_click_is_reported() {
        runComposeUiTest {
            var clicks = 0
            setContent {
                AdaptThemeFadeComposable {
                    OptionComposable(
                        text = "Theme",
                        end = {},
                        onClick = { clicks++ }
                    )
                }
            }

            onNodeWithText("Theme").performClick()

            assertEquals(1, clicks)
        }
    }
}
