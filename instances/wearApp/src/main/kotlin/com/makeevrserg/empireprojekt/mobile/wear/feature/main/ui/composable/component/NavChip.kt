package com.makeevrserg.empireprojekt.mobile.wear.feature.main.ui.composable.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.modules.services.core.resources.R
import com.makeevrserg.empireprojekt.mobile.wear.feature.composable.component.IconTextChip

@Composable
fun NavChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconTextChip(
        text = text,
        painter = painterResource(id = R.drawable.img_splash),
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    )
}

@Preview
@Composable
private fun NavChipPreview() {
    AdaptThemeFadeComposable {
        NavChip(text = "Statuses", onClick = {})
    }
}
