package com.makeevrserg.empireprojekt.mobile.features.ui.status

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.features.root.api.modal.RootBottomSheetRouter
import com.makeevrserg.empireprojekt.mobile.features.status.api.root.component.RootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.api.component.ThemeSwitcherComponent

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
