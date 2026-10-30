package com.makeevrserg.empireprojekt.mobile.core.ui.option.composable.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.makeevrserg.empireprojekt.mobile.core.ui.text.composable.AstraText
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Composable
private fun OptionHrefEndText(endText: String, isActive: Boolean) {
    AnimatedContent(
        targetState = endText,
        transitionSpec = {
            if (targetState > initialState) {
                slideInVertically { height -> height } + fadeIn() togetherWith
                    slideOutVertically { height -> -height } + fadeOut()
            } else {
                slideInVertically { height -> -height } + fadeIn() togetherWith
                    slideOutVertically { height -> height } + fadeOut()
            }.using(SizeTransform(clip = false))
        },
        content = { animatedEndText ->
            AstraText(
                text = animatedEndText,
                color = animateColorAsState(
                    targetValue = when (isActive) {
                        true -> MaterialTheme.colors.onPrimary
                        false -> AppTheme.astraColors.surface.onSecondary
                    }
                ).value,
                fontSize = 16.sp
            )
        }
    )
}

@Composable
private fun OptionHrefEnd(
    endText: String?,
    isActive: Boolean,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        if (endText != null) {
            OptionHrefEndText(endText = endText, isActive = isActive)
        }
        Icon(
            painter = rememberVectorPainter(Icons.AutoMirrored.Filled.KeyboardArrowRight),
            contentDescription = null,
            tint = AppTheme.astraColors.surface.onSecondary,
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .clickable(onClick = onClick)
        )
    }
}

@Composable
fun OptionHref(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    infoText: String? = null,
    endText: String? = null,
    isActive: Boolean = true,
    iconTint: Color = AppTheme.astraColors.surface.onSecondary,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    OptionRow(
        text = text,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(contentPadding)
            .then(modifier),
        icon = icon,
        iconTint = iconTint,
        infoText = infoText,
        end = {
            OptionHrefEnd(
                endText = endText,
                isActive = isActive,
                onClick = onClick
            )
        }
    )
}

private const val PREVIEW_TEXT = "Some text"
private val PreviewLongText = List(size = 10) { PREVIEW_TEXT }.joinToString(separator = " ")

@Composable
private fun OptionHrefPreviewSheet(
    text: String,
    infoText: String?,
    endText: String?,
    isActive: Boolean
) {
    AdaptThemeFade {
        Column {
            OptionHref(
                icon = rememberVectorPainter(Icons.Filled.Image),
                text = text,
                infoText = infoText,
                endText = endText,
                isActive = isActive,
                onClick = {},
                modifier = Modifier.padding(
                    horizontal = 8.dp,
                    vertical = 4.dp
                )
            )
            OptionSeparator(Modifier.fillMaxWidth())
        }
    }
}

@Preview
@Composable
private fun OptionHrefWithEndTextPreview() {
    OptionHrefPreviewSheet(
        text = PREVIEW_TEXT,
        infoText = null,
        endText = "20m",
        isActive = true
    )
}

@Preview
@Composable
private fun OptionHrefInactivePreview() {
    OptionHrefPreviewSheet(
        text = PREVIEW_TEXT,
        infoText = null,
        endText = "20m",
        isActive = false
    )
}

@Preview
@Composable
private fun OptionHrefLongPreview() {
    OptionHrefPreviewSheet(
        text = PreviewLongText,
        infoText = null,
        endText = null,
        isActive = true
    )
}

@Preview
@Composable
private fun OptionHrefWithInfoPreview() {
    OptionHrefPreviewSheet(
        text = PREVIEW_TEXT,
        infoText = PREVIEW_TEXT,
        endText = null,
        isActive = true
    )
}

@Preview
@Composable
private fun OptionHrefLongWithLongInfoPreview() {
    OptionHrefPreviewSheet(
        text = PreviewLongText,
        infoText = PreviewLongText,
        endText = null,
        isActive = true
    )
}
