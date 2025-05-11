package com.makeevrserg.empireprojekt.mobile.core.ui.option

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
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Suppress("LongMethod")
@Composable
fun OptionHref(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    infoText: String? = null,
    endText: String? = null,
    isActive: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    Row(
        modifier = Modifier
            .clickable { onClick.invoke() }
            .padding(contentPadding)
            .then(modifier),
        horizontalArrangement = Arrangement.spacedBy(
            8.dp,
            Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.let { icon ->
            Icon(
                painter = icon,
                contentDescription = null,
                tint = AppTheme.astraColors.surface.onSecondary,
                modifier = Modifier.size(20.dp)
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Start,
                fontSize = 18.sp
            )
            infoText?.let {
                Text(
                    text = infoText,
                    color = AppTheme.astraColors.surface.onSecondary,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Start,
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            endText?.let {
                AnimatedContent(
                    targetState = endText,
                    transitionSpec = {
                        if (targetState > initialState) {
                            slideInVertically { it } + fadeIn() togetherWith slideOutVertically { -it } + fadeOut()
                        } else {
                            slideInVertically { -it } + fadeIn() togetherWith slideOutVertically { it } + fadeOut()
                        }.using(SizeTransform(clip = false))
                    },
                    content = { endText ->
                        Text(
                            text = endText,
                            color = animateColorAsState(
                                targetValue = when (isActive) {
                                    true -> MaterialTheme.colors.onPrimary
                                    false -> AppTheme.astraColors.surface.onSecondary
                                }
                            ).value,
                            fontSize = 16.sp,
                        )
                    }
                )
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
}

internal const val TEXT = "Some text"
internal val LONG_TEXT = List(size = 10) {
    TEXT
}.joinToString(" ", "", "")

@Suppress("LongMethod")
@Composable
@Preview
private fun OptionHrefPreview() {
    val modifier = Modifier.padding(
        horizontal = 8.dp,
        vertical = 4.dp
    )
    Column {
        OptionHref(
            icon = rememberVectorPainter(Icons.Filled.Image),
            text = TEXT,
            endText = "20m",
            onClick = {},
            modifier = modifier
        )
        OptionHref(
            icon = rememberVectorPainter(Icons.Filled.Image),
            text = TEXT,
            endText = "20m",
            onClick = {},
            modifier = modifier,
            isActive = false
        )
        OptionHref(
            icon = rememberVectorPainter(Icons.Filled.Image),
            text = TEXT,
            onClick = {},
            modifier = modifier
        )
        OptionSeparator(Modifier.fillMaxWidth())
        OptionHref(
            icon = rememberVectorPainter(Icons.Filled.Image),
            text = LONG_TEXT,
            onClick = {},
            modifier = modifier
        )
        OptionSeparator(Modifier.fillMaxWidth())
        OptionHref(
            icon = rememberVectorPainter(Icons.Filled.Image),
            text = TEXT,
            infoText = TEXT,
            onClick = {},
            modifier = modifier
        )
        OptionSeparator(Modifier.fillMaxWidth())
        OptionHref(
            icon = rememberVectorPainter(Icons.Filled.Image),
            text = LONG_TEXT,
            infoText = TEXT,
            onClick = {},
            modifier = modifier
        )
        OptionSeparator(Modifier.fillMaxWidth())
        OptionHref(
            icon = rememberVectorPainter(Icons.Filled.Image),
            text = LONG_TEXT,
            infoText = LONG_TEXT,
            onClick = {},
            modifier = modifier
        )
        OptionSeparator(Modifier.fillMaxWidth())
        OptionHref(
            icon = rememberVectorPainter(Icons.Filled.Image),
            text = TEXT,
            infoText = LONG_TEXT,
            onClick = {},
            modifier = modifier
        )
    }
}
