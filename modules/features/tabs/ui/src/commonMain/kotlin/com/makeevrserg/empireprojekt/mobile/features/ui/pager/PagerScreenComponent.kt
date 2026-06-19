package com.makeevrserg.empireprojekt.mobile.features.ui.pager

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.core.ui.decompose.DecomposeScreen
import com.makeevrserg.empireprojekt.mobile.features.root.pager.PagerComponent

@Composable
fun PagerScreenComponent(
    pagerComponent: PagerComponent,
    menuScreen: DecomposeScreen<PagerComponent.Child.Menu>,
    mapScreen: DecomposeScreen<PagerComponent.Child.Map>,
    modifier: Modifier = Modifier
) {
    val selectedChild by pagerComponent.selectedChild.collectAsState()
    val selectedBottomBarItem by pagerComponent.selectedBottomBarItem.collectAsState()
    PagerComposableScreen(
        selectedChild = selectedChild,
        selectedBottomBarItem = selectedBottomBarItem,
        menuScreen = menuScreen,
        mapScreen = mapScreen,
        onTabClick = pagerComponent::select,
        modifier = modifier
    )
}
