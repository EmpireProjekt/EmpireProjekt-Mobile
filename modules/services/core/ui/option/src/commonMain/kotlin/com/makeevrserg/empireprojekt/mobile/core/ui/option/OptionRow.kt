package com.makeevrserg.empireprojekt.mobile.core.ui.option

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.makeevrserg.empireprojekt.mobile.core.ui.text.AstraText
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

private val ICON_SIZE = 24.dp
private val TITLE_FONT_SIZE = 18.sp

@Composable
internal fun OptionRow(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colors.onPrimary,
    icon: Painter? = null,
    iconTint: Color = AppTheme.astraColors.surface.onSecondary,
    infoText: String? = null,
    infoSpacing: Dp = 0.dp,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    end: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS, Alignment.CenterHorizontally),
        verticalAlignment = verticalAlignment
    ) {
        icon?.let { painter ->
            Icon(
                painter = painter,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(ICON_SIZE)
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            AstraText(
                text = text,
                color = textColor,
                textAlign = TextAlign.Start,
                fontSize = TITLE_FONT_SIZE
            )
            infoText?.let { info ->
                if (infoSpacing > 0.dp) {
                    Spacer(Modifier.height(infoSpacing))
                }
                AstraText(
                    text = info,
                    color = AppTheme.astraColors.surface.onSecondary,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Start
                )
            }
        }
        end.invoke(this)
    }
}
