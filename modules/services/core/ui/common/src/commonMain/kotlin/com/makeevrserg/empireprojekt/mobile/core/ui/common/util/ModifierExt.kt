package com.makeevrserg.empireprojekt.mobile.core.ui.common.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.eygraber.compose.placeholder.PlaceholderHighlight
import com.eygraber.compose.placeholder.placeholder
import com.eygraber.compose.placeholder.shimmer
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.theme.AppTheme

fun Modifier.navBarsPadding(): Modifier = then(navigationBarsPadding())

fun Modifier.astraCard(color: Color, shape: Shape = RoundedCornerShape(AppTheme.dimens.S)): Modifier {
    return clip(shape).background(color)
}

fun Modifier.placeholder(
    visible: Boolean,
    color: Color,
    highlightColor: Color,
    shape: Shape = RoundedCornerShape(AppTheme.dimens.XS),
): Modifier {
    return then(
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
