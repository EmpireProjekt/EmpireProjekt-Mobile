@file:OptIn(ExperimentalTestApi::class)

package com.makeevrserg.empireprojekt.mobile.core.ui.option.composable.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertTopPositionInRootIsEqualTo
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.option.model.OptionSectionContent
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class OptionSectionTest {
    @Test
    fun GIVEN_several_items_WHEN_section_is_shown_THEN_rows_are_divided_by_one_pixel_separators() {
        runComposeUiTest {
            setContent {
                AdaptThemeFade {
                    OptionSection(
                        section = OptionSectionContent(
                            items = listOf(
                                "first",
                                "second",
                                "third"
                            )
                        ),
                        modifier = Modifier.testTag(SECTION_TAG)
                    ) { title ->
                        Box(Modifier.testTag(title).fillMaxWidth().height(10.dp))
                    }
                }
            }

            onNodeWithTag("first").assertTopPositionInRootIsEqualTo(0.dp)
            onNodeWithTag("second").assertTopPositionInRootIsEqualTo(11.dp)
            onNodeWithTag("third").assertTopPositionInRootIsEqualTo(22.dp)
            onNodeWithTag(SECTION_TAG).assertHeightIsEqualTo(32.dp)
        }
    }

    @Test
    fun GIVEN_single_item_WHEN_section_is_shown_THEN_no_separator_is_added() {
        runComposeUiTest {
            setContent {
                AdaptThemeFade {
                    OptionSection(
                        section = OptionSectionContent(items = listOf("only")),
                        modifier = Modifier.testTag(SECTION_TAG)
                    ) { title ->
                        Box(Modifier.testTag(title).width(10.dp).height(10.dp))
                    }
                }
            }

            onAllNodesWithTag("only").assertCountEquals(1)
            onNodeWithTag(SECTION_TAG).assertHeightIsEqualTo(10.dp)
        }
    }

    companion object {
        private const val SECTION_TAG = "section"
    }
}
