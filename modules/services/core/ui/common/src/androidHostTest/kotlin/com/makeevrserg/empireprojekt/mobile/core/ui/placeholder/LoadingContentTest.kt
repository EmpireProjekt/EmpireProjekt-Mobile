@file:Suppress("FunctionNaming", "MagicNumber")
@file:OptIn(ExperimentalTestApi::class)

package com.makeevrserg.empireprojekt.mobile.core.ui.placeholder

import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LoadingContentTest {
    @Test
    fun GIVEN_area_WHEN_loading_is_shown_THEN_it_takes_the_given_area() = runComposeUiTest {
        setContent {
            AdaptThemeFade {
                LoadingContent(Modifier.testTag(LOADING_TAG).size(200.dp))
            }
        }

        onNodeWithTag(LOADING_TAG)
            .assertWidthIsEqualTo(200.dp)
            .assertHeightIsEqualTo(200.dp)
    }

    companion object {
        private const val LOADING_TAG = "loading"
    }
}
