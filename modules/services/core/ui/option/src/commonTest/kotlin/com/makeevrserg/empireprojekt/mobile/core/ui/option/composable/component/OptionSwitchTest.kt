@file:OptIn(ExperimentalTestApi::class)

package com.makeevrserg.empireprojekt.mobile.core.ui.option.composable.component

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable.AdaptThemeFadeComposable
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class OptionSwitchTest {
    @Test
    fun GIVEN_unchecked_switch_WHEN_row_is_clicked_THEN_checked_state_is_requested() {
        runComposeUiTest {
            val requestedStates = mutableListOf<Boolean>()
            setContent {
                AdaptThemeFadeComposable {
                    OptionSwitchComposable(
                        text = SWITCH_TEXT,
                        checked = false,
                        onCheckChange = { isChecked -> requestedStates += isChecked }
                    )
                }
            }

            onNodeWithText(SWITCH_TEXT).performClick()

            assertEquals(listOf(true), requestedStates)
        }
    }

    @Test
    fun GIVEN_checked_switch_WHEN_row_is_clicked_THEN_unchecked_state_is_requested() {
        runComposeUiTest {
            val requestedStates = mutableListOf<Boolean>()
            setContent {
                AdaptThemeFadeComposable {
                    OptionSwitchComposable(
                        text = SWITCH_TEXT,
                        checked = true,
                        onCheckChange = { isChecked -> requestedStates += isChecked }
                    )
                }
            }

            onNodeWithText(SWITCH_TEXT).performClick()

            assertEquals(listOf(false), requestedStates)
        }
    }

    @Test
    fun GIVEN_disabled_switch_WHEN_row_is_clicked_THEN_nothing_is_requested() {
        runComposeUiTest {
            val requestedStates = mutableListOf<Boolean>()
            setContent {
                AdaptThemeFadeComposable {
                    OptionSwitchComposable(
                        text = SWITCH_TEXT,
                        checked = true,
                        isEnabled = false,
                        onCheckChange = { isChecked -> requestedStates += isChecked }
                    )
                }
            }

            onNodeWithText(SWITCH_TEXT).performClick()

            assertEquals(emptyList<Boolean>(), requestedStates)
        }
    }

    companion object {
        private const val SWITCH_TEXT = "Dark theme"
    }
}
