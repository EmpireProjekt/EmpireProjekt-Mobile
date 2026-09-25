@file:Suppress("FunctionNaming")
@file:OptIn(ExperimentalTestApi::class)

package com.makeevrserg.empireprojekt.mobile.core.ui.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AstraIconButtonTest {
    @Test
    fun GIVEN_icon_button_WHEN_clicked_THEN_click_is_reported() = runComposeUiTest {
        var clicks = 0
        setContent {
            AdaptThemeFade {
                AstraIconButton(
                    imageVector = Icons.Filled.Search,
                    contentDescription = SEARCH_DESCRIPTION,
                    onClick = { clicks++ }
                )
            }
        }

        onNodeWithContentDescription(SEARCH_DESCRIPTION).performClick()

        assertEquals(1, clicks)
    }

    @Test
    fun GIVEN_back_button_WHEN_clicked_THEN_back_is_reported() = runComposeUiTest {
        var backPresses = 0
        setContent {
            AdaptThemeFade {
                AstraBackButton(onClick = { backPresses++ }, modifier = Modifier.testTag(BACK_TAG))
            }
        }

        onNodeWithTag(BACK_TAG).performClick()

        assertEquals(1, backPresses)
    }

    companion object {
        private const val SEARCH_DESCRIPTION = "search"
        private const val BACK_TAG = "back"
    }
}
