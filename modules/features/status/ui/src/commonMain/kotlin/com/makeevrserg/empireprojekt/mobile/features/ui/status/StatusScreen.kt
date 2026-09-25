package com.makeevrserg.empireprojekt.mobile.features.ui.status

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.features.root.modal.RootBottomSheetRouter
import com.makeevrserg.empireprojekt.mobile.features.status.root.presentation.RootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.presentation.ThemeSwitcherComponent

@Composable
fun StatusScreen(
    rootBottomSheetRouter: RootBottomSheetRouter,
    themeSwitcherComponent: ThemeSwitcherComponent,
    rootStatusComponent: RootStatusComponent,
    modifier: Modifier = Modifier
) {
    StatusComposableScreen(
        statusComponents = rootStatusComponent.statusComponents,
        onThemeClick = themeSwitcherComponent::next,
        onInfoClick = rootBottomSheetRouter::showInfoSheet,
        modifier = modifier
    )
}
