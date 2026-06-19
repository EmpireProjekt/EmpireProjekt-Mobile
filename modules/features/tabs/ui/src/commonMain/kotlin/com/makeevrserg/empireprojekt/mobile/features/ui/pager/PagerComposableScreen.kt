package com.makeevrserg.empireprojekt.mobile.features.ui.pager

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.decompose.DecomposeScreen
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.features.root.pager.PagerComponent
import com.makeevrserg.empireprojekt.mobile.features.root.pager.model.PagerBottomBarItem
import com.makeevrserg.empireprojekt.mobile.features.ui.pager.components.PagerBottomBar

@Composable
internal fun PagerComposableScreen(
    selectedChild: PagerComponent.Child,
    selectedBottomBarItem: PagerBottomBarItem,
    menuScreen: DecomposeScreen<PagerComponent.Child.Menu>,
    mapScreen: DecomposeScreen<PagerComponent.Child.Map>,
    onTabClick: (PagerBottomBarItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Crossfade(
            targetState = selectedChild,
            modifier = Modifier.fillMaxWidth(),
            label = "Crossfade instance composable"
        ) { instance ->
            when (instance) {
                is PagerComponent.Child.Menu -> menuScreen.Render(
                    child = instance,
                    modifier = Modifier
                )

                is PagerComponent.Child.Map -> mapScreen.Render(
                    child = instance,
                    modifier = Modifier
                )
            }
        }
        PagerBottomBar(
            selectedIndex = selectedBottomBarItem.ordinal,
            onTabClick = onTabClick,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

private fun <T : Any> previewPagerScreen(label: String): DecomposeScreen<T> =
    DecomposeScreen { renderModifier, _ ->
        Box(
            modifier = renderModifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }

@Preview
@Composable
private fun PagerComposableScreenMenuPreview() {
    AdaptThemeFade {
        PagerComposableScreen(
            selectedChild = PagerComponent.Child.Menu,
            selectedBottomBarItem = PagerBottomBarItem.Menu,
            menuScreen = previewPagerScreen(label = "Menu"),
            mapScreen = previewPagerScreen(label = "Map"),
            onTabClick = {}
        )
    }
}

@Preview
@Composable
private fun PagerComposableScreenMapPreview() {
    AdaptThemeFade {
        PagerComposableScreen(
            selectedChild = PagerComponent.Child.Map,
            selectedBottomBarItem = PagerBottomBarItem.Map,
            menuScreen = previewPagerScreen(label = "Menu"),
            mapScreen = previewPagerScreen(label = "Map"),
            onTabClick = {}
        )
    }
}
