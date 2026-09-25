package com.makeevrserg.empireprojekt.mobile.core.ui.common

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

private val AVATAR_SIZE = 32.dp

@Composable
fun PlayerAvatar(uuid: String, modifier: Modifier = Modifier) {
    PlayerHeadBox(
        uuid = uuid,
        modifier = modifier
            .size(AVATAR_SIZE)
            .clip(RoundedCornerShape(AppTheme.dimens.XXS))
    )
}

@Preview
@Composable
private fun PlayerAvatarPreview() {
    AdaptThemeFade {
        PlayerAvatar(uuid = "069a79f4-44e9-4726-a5be-fca90e38aaf5")
    }
}
