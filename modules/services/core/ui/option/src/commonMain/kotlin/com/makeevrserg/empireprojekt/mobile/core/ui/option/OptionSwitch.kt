package com.makeevrserg.empireprojekt.mobile.core.ui.option

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Suppress("LongMethod")
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
    Row(
        modifier = modifier
            .clickable(enabled = isEnabled) { onCheckChange.invoke(checked) }
            .padding(contentPadding),
        horizontalArrangement = Arrangement.spacedBy(
            8.dp,
            Alignment.CenterHorizontally
        ),
        verticalAlignment = verticalAlignment
    ) {
        icon?.let { icon ->
            Icon(
                painter = icon,
                contentDescription = null,
                tint = AppTheme.astraColors.surface.onSecondary,
                modifier = Modifier.size(28.dp)
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                color = animateColorAsState(
                    targetValue = when (checked) {
                        true -> MaterialTheme.colors.onPrimary

                        false -> AppTheme.astraColors.surface.onSecondary
                    }
                ).value,
                textAlign = TextAlign.Start,
                fontSize = 18.sp
            )
            infoText?.let {
                Spacer(Modifier.height(6.dp))
                Text(
                    text = infoText,
                    color = AppTheme.astraColors.surface.onSecondary,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Start,
                )
            }
        }
        M3Switch(
            checked = checked,
            onCheckedChange = onCheckChange,
            enabled = isEnabled,
        )
    }
}

@Composable
@Preview
@Suppress("LongMethod")
private fun OptionSwitchPreview() {
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
