@file:Suppress("FunctionNaming")
@file:OptIn(ExperimentalTestApi::class)

package com.makeevrserg.empireprojekt.mobile.core.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class AstraCardTest {
    @Test
    fun GIVEN_card_WHEN_drawn_THEN_it_is_filled_with_primary_and_its_corners_are_rounded() = runComposeUiTest {
        var primary = Color.Unspecified
        setContent {
            AdaptThemeFade {
                primary = MaterialTheme.colors.primary
                Box(Modifier.testTag(CARD_TAG).size(64.dp).astraCard())
            }
        }

        val pixels = onNodeWithTag(CARD_TAG).captureToImage().toPixelMap()
        assertEquals(primary, pixels[pixels.width / 2, pixels.height / 2])
        assertNotEquals(primary, pixels[0, 0])
    }

    companion object {
        private const val CARD_TAG = "card"
    }
}
