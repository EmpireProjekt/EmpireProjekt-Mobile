@file:Suppress("FunctionNaming")
@file:OptIn(ExperimentalTestApi::class)

package com.makeevrserg.empireprojekt.mobile.core.ui.filtercard

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.resources.common_filter_multiple_sort_desc
import com.makeevrserg.empireprojekt.mobile.core.resources.common_filter_title
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class SortFilterHeaderTest {
    @Test
    fun GIVEN_filter_card_WHEN_sort_header_is_shown_THEN_title_and_multiple_sort_hint_are_displayed() =
        runComposeUiTest {
            val context = RuntimeEnvironment.getApplication()
            setContent {
                AdaptThemeFade {
                    FilterCard {
                        SortFilterHeader()
                    }
                }
            }

            val title = context.getString(MR.strings.common_filter_title.resourceId)
            val hint = context.getString(MR.strings.common_filter_multiple_sort_desc.resourceId)
            onNodeWithText(title).assertIsDisplayed()
            onNodeWithText(hint).assertIsDisplayed()
        }
}
