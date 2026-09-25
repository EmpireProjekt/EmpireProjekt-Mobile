@file:Suppress("FunctionNaming")
@file:OptIn(ExperimentalTestApi::class)

package com.makeevrserg.empireprojekt.mobile.core.ui.appbar

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import com.makeevrserg.empireprojekt.mobile.core.ui.text.AstraText
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AstraTopBarScreenTest {
    @Test
    fun GIVEN_screen_WHEN_shown_THEN_content_is_placed_below_the_titled_bar() = runComposeUiTest {
        setContent {
            AdaptThemeFade {
                AstraTopBarScreen(title = "Menu") {
                    AstraText(text = "Links")
                }
            }
        }

        onNodeWithText("Menu").assertIsDisplayed()
        val titleBottom = onNodeWithText("Menu").getBoundsInRoot().bottom
        val contentTop = onNodeWithText("Links").getBoundsInRoot().top
        assertTrue(contentTop >= titleBottom)
    }
}
