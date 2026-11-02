package com.makeevrserg.empireprojekt.mobile.features.tabs.ui.composable.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.core.ui.common.decompose.model.DecomposeScreen
import com.makeevrserg.empireprojekt.mobile.features.root.api.pager.component.PagerComponent

@Composable
fun PagerScreenHostComposable(
    pagerComponent: PagerComponent,
    menuScreen: DecomposeScreen<PagerComponent.Child.Menu>,
    mapScreen: DecomposeScreen<PagerComponent.Child.Map>,
    modifier: Modifier = Modifier
) {
    val selectedChild by pagerComponent.selectedChild.collectAsState()
    val selectedBottomBarItem by pagerComponent.selectedBottomBarItem.collectAsState()
    PagerScreenComposable(
        selectedChild = selectedChild,
        selectedBottomBarItem = selectedBottomBarItem,
        menuScreen = menuScreen,
        mapScreen = mapScreen,
        onTabClick = pagerComponent::select,
        modifier = modifier
    )
}
