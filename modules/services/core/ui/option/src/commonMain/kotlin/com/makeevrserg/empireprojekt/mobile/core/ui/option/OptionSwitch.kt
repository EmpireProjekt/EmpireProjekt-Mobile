package com.makeevrserg.empireprojekt.mobile.core.ui.option

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

private val INFO_SPACING = 6.dp

@Composable
fun OptionSwitch(
    text: String,
    onCheckChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    isEnabled: Boolean = true,
    checked: Boolean = true,
    infoText: String? = null,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val textColor by animateColorAsState(
        targetValue = when (checked) {
            true -> MaterialTheme.colors.onPrimary
            false -> AppTheme.astraColors.surface.onSecondary
        }
    )
    OptionRow(
        text = text,
        modifier = modifier
            .clickable(enabled = isEnabled) { onCheckChange.invoke(!checked) }
            .padding(contentPadding),
        textColor = textColor,
        icon = icon,
        infoText = infoText,
        infoSpacing = INFO_SPACING,
        verticalAlignment = verticalAlignment,
        end = {
            M3Switch(
                checked = checked,
                onCheckedChange = onCheckChange,
                enabled = isEnabled,
            )
        }
    )
}

@Composable
@Preview
@Suppress("LongMethod")
private fun OptionSwitchPreview() {
    AdaptThemeFade {
        val modifier = Modifier.padding(
            horizontal = 8.dp,
            vertical = 4.dp
        )
        Column {
            OptionSwitch(
                icon = rememberVectorPainter(Icons.Filled.Image),
                text = TEXT,
                onCheckChange = {},
                modifier = modifier
            )
            OptionSeparator(Modifier.fillMaxWidth())
            OptionSwitch(
                icon = rememberVectorPainter(Icons.Filled.Image),
                text = LONG_TEXT,
                checked = false,
                onCheckChange = {},
                modifier = modifier
            )
            OptionSeparator(Modifier.fillMaxWidth())
            OptionSwitch(
                icon = rememberVectorPainter(Icons.Filled.Image),
                text = TEXT,
                infoText = TEXT,
                checked = true,
                isEnabled = false,
                onCheckChange = {},
                modifier = modifier
            )
            OptionSeparator(Modifier.fillMaxWidth())
            OptionSwitch(
                icon = rememberVectorPainter(Icons.Filled.Image),
                text = LONG_TEXT,
                infoText = TEXT,
                checked = false,
                isEnabled = true,
                onCheckChange = {},
                modifier = modifier
            )
            OptionSeparator(Modifier.fillMaxWidth())
            OptionSwitch(
                icon = rememberVectorPainter(Icons.Filled.Image),
                text = LONG_TEXT,
                infoText = LONG_TEXT,
                onCheckChange = {},
                modifier = modifier
            )
            OptionSeparator(Modifier.fillMaxWidth())
            OptionSwitch(
                icon = rememberVectorPainter(Icons.Filled.Image),
                text = TEXT,
                infoText = LONG_TEXT,
                onCheckChange = {},
                modifier = modifier
            )
        }
    }
}
