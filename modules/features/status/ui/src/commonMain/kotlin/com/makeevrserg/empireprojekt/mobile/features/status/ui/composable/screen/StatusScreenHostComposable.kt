package com.makeevrserg.empireprojekt.mobile.features.status.ui.composable.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.features.root.api.api.RootBottomSheetRouter
import com.makeevrserg.empireprojekt.mobile.features.status.api.root.component.RootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.api.component.ThemeSwitcherComponent

@Composable
fun StatusScreenHostComposable(
    rootBottomSheetRouter: RootBottomSheetRouter,
    themeSwitcherComponent: ThemeSwitcherComponent,
    rootStatusComponent: RootStatusComponent,
    modifier: Modifier = Modifier
) {
    StatusScreenComposable(
        statusComponents = rootStatusComponent.statusComponents,
        onThemeClick = themeSwitcherComponent::next,
        onInfoClick = rootBottomSheetRouter::showInfoSheet,
        modifier = modifier
    )
}
