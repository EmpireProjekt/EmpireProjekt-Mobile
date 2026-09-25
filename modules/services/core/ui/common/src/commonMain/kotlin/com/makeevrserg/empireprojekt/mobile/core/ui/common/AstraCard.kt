package com.makeevrserg.empireprojekt.mobile.core.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Composable
@Suppress("ModifierComposable")
fun Modifier.astraCard(shape: Shape = RoundedCornerShape(AppTheme.dimens.S)): Modifier {
    return clip(shape).background(MaterialTheme.colors.primary)
}
