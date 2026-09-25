@file:Suppress("FunctionNaming", "MagicNumber")
@file:OptIn(ExperimentalTestApi::class)

package com.makeevrserg.empireprojekt.mobile.core.ui.common

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertLeftPositionInRootIsEqualTo
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PlayerNameRowTest {
    @Test
    fun GIVEN_player_WHEN_row_is_shown_THEN_name_follows_the_avatar_after_default_spacing() = runComposeUiTest {
        setContent {
            AdaptThemeFade {
                PlayerNameRow(uuid = "uuid", name = "RomaRoman")
            }
        }

        onNodeWithText("RomaRoman")
            .assertIsDisplayed()
            .assertLeftPositionInRootIsEqualTo(40.dp)
    }

    @Test
    fun GIVEN_custom_spacing_WHEN_row_is_shown_THEN_name_moves_by_that_spacing() = runComposeUiTest {
        setContent {
            AdaptThemeFade {
                PlayerNameRow(uuid = "uuid", name = "RomaRoman", spacing = 16.dp)
            }
        }

        onNodeWithText("RomaRoman").assertLeftPositionInRootIsEqualTo(48.dp)
    }
}
