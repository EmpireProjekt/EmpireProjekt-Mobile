package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.main.composable.screen

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.Scaffold
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.resources.wear_main_ping_action
import com.makeevrserg.empireprojekt.mobile.core.resources.wear_main_statuses_action
import com.makeevrserg.empireprojekt.mobile.core.ui.common.util.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.theme.model.ComposeTheme
import com.makeevrserg.empireprojekt.mobile.features.theme.api.data.model.Theme
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.main.composable.component.NavChipComposable
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.main.composable.component.ThemeChipComposable

@Composable
internal fun MainScreenComposable(
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
            ThemeChipComposable(
                theme = theme,
                onClick = onThemeClick
            )
            Spacer(modifier = Modifier.height(AppTheme.dimens.S))
            NavChipComposable(
                text = MR.strings.wear_main_statuses_action.asComposableString(),
                onClick = onOpenStatusesClick
            )
            Spacer(modifier = Modifier.height(AppTheme.dimens.S))
            NavChipComposable(
                text = MR.strings.wear_main_ping_action.asComposableString(),
                onClick = onOpenPingClick
            )
        }
    }
}

@Preview(device = "id:wearos_large_round")
@Composable
private fun MainScreenDarkPreview() {
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.DARK) {
        MainScreenComposable(
            theme = Theme.DARK,
            onThemeClick = {},
            onOpenStatusesClick = {},
            onOpenPingClick = {}
        )
    }
}

@Preview(device = "id:wearos_large_round")
@Composable
private fun MainScreenLightPreview() {
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.LIGHT) {
        MainScreenComposable(
            theme = Theme.LIGHT,
            onThemeClick = {},
            onOpenStatusesClick = {},
            onOpenPingClick = {}
        )
    }
}
