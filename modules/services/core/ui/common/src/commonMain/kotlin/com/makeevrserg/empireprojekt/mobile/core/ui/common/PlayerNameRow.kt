package com.makeevrserg.empireprojekt.mobile.core.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.makeevrserg.empireprojekt.mobile.core.ui.text.AstraText
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Composable
fun PlayerNameRow(
    uuid: String,
    name: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.h6,
    spacing: Dp = AppTheme.dimens.XS,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing)
    ) {
        PlayerAvatar(uuid = uuid)
        AstraText(
            text = name,
            style = style,
            color = MaterialTheme.colors.onPrimary
        )
    }
}

@Preview
@Composable
private fun PlayerNameRowPreview() {
    AdaptThemeFade {
        PlayerNameRow(uuid = "069a79f4-44e9-4726-a5be-fca90e38aaf5", name = "RomaRoman")
    }
}
