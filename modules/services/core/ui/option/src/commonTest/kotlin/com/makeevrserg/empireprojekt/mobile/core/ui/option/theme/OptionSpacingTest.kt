@file:OptIn(ExperimentalTestApi::class)

package com.makeevrserg.empireprojekt.mobile.core.ui.option.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertLeftPositionInRootIsEqualTo
import androidx.compose.ui.test.assertTopPositionInRootIsEqualTo
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class OptionSpacingTest {
    @Test
    fun GIVEN_option_content_padding_WHEN_applied_to_a_row_THEN_row_content_is_inset_on_every_side() {
        runComposeUiTest {
            setContent {
                AdaptThemeFade {
                    Box(Modifier.testTag(ROW_TAG).padding(OptionSpacing.ContentPadding)) {
                        Box(Modifier.testTag(CONTENT_TAG).width(10.dp).height(10.dp))
                    }
                }
            }

            onNodeWithTag(CONTENT_TAG)
                .assertLeftPositionInRootIsEqualTo(8.dp)
                .assertTopPositionInRootIsEqualTo(8.dp)
            onNodeWithTag(ROW_TAG).assertHeightIsEqualTo(26.dp)
        }
    }

    companion object {
        private const val ROW_TAG = "row"
        private const val CONTENT_TAG = "content"
    }
}
