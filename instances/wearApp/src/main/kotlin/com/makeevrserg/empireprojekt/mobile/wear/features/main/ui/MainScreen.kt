package com.makeevrserg.empireprojekt.mobile.wear.features.main.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.features.theme.presentation.ThemeSwitcherComponent

@Composable
fun MainScreen(
    themeSwitcherComponent: ThemeSwitcherComponent,
    onOpenStatusesClick: () -> Unit,
    onOpenPingClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val theme by themeSwitcherComponent.theme.collectAsState()
    MainComposableScreen(
        theme = theme,
        onThemeClick = themeSwitcherComponent::next,
        onOpenStatusesClick = onOpenStatusesClick,
        onOpenPingClick = onOpenPingClick,
        modifier = modifier
    )
}
