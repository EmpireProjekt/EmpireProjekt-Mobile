package com.makeevrserg.empireprojekt.mobile.core.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.resources.img_head_error
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asPainter

@Composable
fun PlayerHeadBox(uuid: String, modifier: Modifier = Modifier) {
    SubcomposeAsyncImage(
        model = "https://mc-heads.net/avatar/$uuid",
        contentDescription = null,
        modifier = modifier,
        loading = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .placeholder(true)
                    .background(MaterialTheme.colors.primaryVariant)
                    .clip(RoundedCornerShape(AppTheme.dimens.XXS))
            )
        },
        error = {
            Icon(
                painter = MR.images.img_head_error.asPainter(),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
    )
}

@Preview
@Composable
private fun PlayerHeadBoxPreview() {
    AdaptThemeFade {
        PlayerHeadBox(
            uuid = "069a79f4-44e9-4726-a5be-fca90e38aaf5",
            modifier = Modifier.size(64.dp)
        )
    }
}
