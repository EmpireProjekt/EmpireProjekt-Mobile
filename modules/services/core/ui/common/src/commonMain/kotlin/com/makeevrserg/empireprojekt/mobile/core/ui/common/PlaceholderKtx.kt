package com.makeevrserg.empireprojekt.mobile.core.ui.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.eygraber.compose.placeholder.PlaceholderHighlight
import com.eygraber.compose.placeholder.placeholder
import com.eygraber.compose.placeholder.shimmer
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Composable
@Suppress("ModifierComposable")
fun Modifier.placeholder(
    visible: Boolean,
    shape: Shape = RoundedCornerShape(AppTheme.dimens.XS),
    highlightColor: Color = MaterialTheme.colors.primary,
    color: Color = MaterialTheme.colors.primaryVariant,
): Modifier {
    return this.then(
        placeholder(
            visible = visible,
            color = color,
            shape = shape,
            highlight = PlaceholderHighlight.shimmer(
                highlightColor = highlightColor
            )
        )
    )
}
