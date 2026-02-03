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
import androidx.wear.compose.material.Scaffold
import com.makeevrserg.empireprojekt.mobile.R
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.theme.presentation.ThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.wear.features.main.ui.components.NavChip
import com.makeevrserg.empireprojekt.mobile.wear.features.main.ui.components.ThemeChip

@Composable
fun MainScreen(
    themeSwitcherComponent: ThemeSwitcherComponent,
    onOpenStatusesClicked: () -> Unit,
    onOpenPingClicked: () -> Unit
) {
    Scaffold(
        modifier = Modifier.background(MaterialTheme.colors.primaryVariant),
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
            ThemeChip(themeSwitcherComponent = themeSwitcherComponent)
            Spacer(modifier = Modifier.height(AppTheme.dimens.S))
            NavChip(
                text = stringResource(R.string.wear_statuses),
                onClick = onOpenStatusesClicked
            )
            Spacer(modifier = Modifier.height(AppTheme.dimens.S))
            NavChip(
                text = stringResource(R.string.wear_ping),
                onClick = onOpenPingClicked
            )
        }
    }
}
