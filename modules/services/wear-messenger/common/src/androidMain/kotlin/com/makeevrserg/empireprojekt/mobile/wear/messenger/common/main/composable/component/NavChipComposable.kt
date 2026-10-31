package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.main.composable.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.resources.img_splash
import com.makeevrserg.empireprojekt.mobile.core.ui.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asPainter
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.composable.component.IconTextChipComposable

@Composable
internal fun NavChipComposable(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconTextChipComposable(
        text = text,
        painter = MR.images.img_splash.asPainter(),
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    )
}

@Preview
@Composable
private fun NavChipPreview() {
    AdaptThemeFadeComposable {
        NavChipComposable(
            text = "Statuses",
            onClick = {}
        )
    }
}
