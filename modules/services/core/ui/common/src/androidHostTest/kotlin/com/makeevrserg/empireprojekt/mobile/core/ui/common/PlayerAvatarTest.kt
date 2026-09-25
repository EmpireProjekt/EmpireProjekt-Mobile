@file:Suppress("FunctionNaming")
@file:OptIn(ExperimentalTestApi::class)

package com.makeevrserg.empireprojekt.mobile.core.ui.common

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
class PlayerAvatarTest {
    @Test
    fun GIVEN_player_WHEN_avatar_is_shown_THEN_it_has_the_list_avatar_size() = runComposeUiTest {
        setContent {
            AdaptThemeFade {
                PlayerAvatar(uuid = "uuid", modifier = Modifier.testTag(AVATAR_TAG))
            }
        }

        onNodeWithTag(AVATAR_TAG)
            .assertWidthIsEqualTo(32.dp)
            .assertHeightIsEqualTo(32.dp)
    }

    companion object {
        private const val AVATAR_TAG = "avatar"
    }
}
