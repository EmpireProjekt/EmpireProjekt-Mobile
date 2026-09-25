package com.makeevrserg.empireprojekt.mobile.wear.features.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.Scaffold
import com.makeevrserg.empireprojekt.mobile.R
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.ComposeTheme
import com.makeevrserg.empireprojekt.mobile.features.theme.data.model.Theme
import com.makeevrserg.empireprojekt.mobile.wear.features.main.ui.components.NavChip
import com.makeevrserg.empireprojekt.mobile.wear.features.main.ui.components.ThemeChip

@Composable
internal fun MainComposableScreen(
    theme: Theme,
    onThemeClick: () -> Unit,
    onOpenStatusesClick: () -> Unit,
    onOpenPingClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.background(MaterialTheme.colors.primaryVariant),
        positionIndicator = {
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AppTheme.dimens.M),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(AppTheme.dimens.S))
            ThemeChip(theme = theme, onClick = onThemeClick)
            Spacer(modifier = Modifier.height(AppTheme.dimens.S))
            NavChip(
                text = stringResource(R.string.wear_main_statuses_action),
                onClick = onOpenStatusesClick
            )
            Spacer(modifier = Modifier.height(AppTheme.dimens.S))
            NavChip(
                text = stringResource(R.string.wear_main_ping_action),
                onClick = onOpenPingClick
            )
        }
    }
}

@Preview(device = "id:wearos_large_round")
@Composable
private fun MainComposableScreenDarkPreview() {
    AdaptThemeFade(composeTheme = ComposeTheme.DARK) {
        MainComposableScreen(
            theme = Theme.DARK,
            onThemeClick = {},
            onOpenStatusesClick = {},
            onOpenPingClick = {}
        )
    }
}

@Preview(device = "id:wearos_large_round")
@Composable
private fun MainComposableScreenLightPreview() {
    AdaptThemeFade(composeTheme = ComposeTheme.LIGHT) {
        MainComposableScreen(
            theme = Theme.LIGHT,
            onThemeClick = {},
            onOpenStatusesClick = {},
            onOpenPingClick = {}
        )
    }
}
