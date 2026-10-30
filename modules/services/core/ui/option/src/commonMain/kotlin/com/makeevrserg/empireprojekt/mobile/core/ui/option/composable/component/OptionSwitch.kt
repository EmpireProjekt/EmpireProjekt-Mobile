package com.makeevrserg.empireprojekt.mobile.core.ui.option.composable.component

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
        infoSpacing = 6.dp,
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

private const val PREVIEW_TEXT = "Some text"
private val PreviewLongText = List(size = 10) { PREVIEW_TEXT }.joinToString(separator = " ")

@Composable
private fun OptionSwitchPreviewSheet(
    text: String,
    infoText: String?,
    checked: Boolean,
    isEnabled: Boolean
) {
    AdaptThemeFade {
        Column {
            OptionSwitch(
                icon = rememberVectorPainter(Icons.Filled.Image),
                text = text,
                infoText = infoText,
                checked = checked,
                isEnabled = isEnabled,
                onCheckChange = {},
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
private fun OptionSwitchCheckedPreview() {
    OptionSwitchPreviewSheet(
        text = PREVIEW_TEXT,
        infoText = null,
        checked = true,
        isEnabled = true
    )
}

@Preview
@Composable
private fun OptionSwitchUncheckedPreview() {
    OptionSwitchPreviewSheet(
        text = PreviewLongText,
        infoText = null,
        checked = false,
        isEnabled = true
    )
}

@Preview
@Composable
private fun OptionSwitchDisabledPreview() {
    OptionSwitchPreviewSheet(
        text = PREVIEW_TEXT,
        infoText = PREVIEW_TEXT,
        checked = true,
        isEnabled = false
    )
}

@Preview
@Composable
private fun OptionSwitchLongInfoPreview() {
    OptionSwitchPreviewSheet(
        text = PreviewLongText,
        infoText = PreviewLongText,
        checked = false,
        isEnabled = true
    )
}
