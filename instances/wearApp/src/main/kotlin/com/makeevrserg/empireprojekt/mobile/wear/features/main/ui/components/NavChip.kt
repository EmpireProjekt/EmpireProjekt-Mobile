package com.makeevrserg.empireprojekt.mobile.wear.features.main.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.makeevrserg.empireprojekt.mobile.modules.services.core.resources.R
import com.makeevrserg.empireprojekt.mobile.wear.features.components.IconTextChip

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
