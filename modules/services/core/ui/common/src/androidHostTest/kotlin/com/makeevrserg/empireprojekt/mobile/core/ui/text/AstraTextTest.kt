@file:Suppress("FunctionNaming")
@file:OptIn(ExperimentalTestApi::class)

package com.makeevrserg.empireprojekt.mobile.core.ui.text

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.resources.jetbrainsmono_wght
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asFontFamily
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AstraTextTest {
    @Test
    fun GIVEN_styled_text_WHEN_shown_THEN_it_keeps_the_style_and_uses_the_design_system_font() = runComposeUiTest {
        var expectedFontFamily: FontFamily? = null
        var expectedFontSize = TextUnit.Unspecified
        setContent {
            AdaptThemeFade {
                expectedFontFamily = MR.fonts.jetbrainsmono_wght.asFontFamily()
                expectedFontSize = MaterialTheme.typography.h6.fontSize
                AstraText(
                    text = "Empire",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.End
                )
            }
        }

        onNodeWithText("Empire").assertIsDisplayed()
        val layoutResults = mutableListOf<TextLayoutResult>()
        onNodeWithText("Empire").fetchSemanticsNode()
            .config[SemanticsActions.GetTextLayoutResult]
            .action
            ?.invoke(layoutResults)
        val style = layoutResults.single().layoutInput.style
        assertEquals(expectedFontFamily, style.fontFamily)
        assertEquals(expectedFontSize, style.fontSize)
        assertEquals(TextAlign.End, style.textAlign)
    }
}
