package com.makeevrserg.empireprojekt.mobile.core.ui.player.composable

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Composable
fun PlayerAvatar(uuid: String, modifier: Modifier = Modifier) {
    PlayerHeadBox(
        uuid = uuid,
        modifier = modifier
            .size(32.dp)
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
