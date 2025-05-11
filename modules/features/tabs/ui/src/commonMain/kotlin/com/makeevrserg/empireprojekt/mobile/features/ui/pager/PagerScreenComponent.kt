package com.makeevrserg.empireprojekt.mobile.features.ui.pager

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.core.ui.decompose.DecomposeScreen
import com.makeevrserg.empireprojekt.mobile.features.root.pager.PagerComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.pager.components.PagerBottomBar

@Composable
fun PagerScreenComponent(
    pagerComponent: PagerComponent,
    modifier: Modifier = Modifier,
    ratingUsersScreen: DecomposeScreen<PagerComponent.Child.RatingUsers>,
    statusScreen: DecomposeScreen<PagerComponent.Child.Status>,
    townsScreen: DecomposeScreen<PagerComponent.Child.Towns>,
    mapScreen: DecomposeScreen<PagerComponent.Child.Map>
) {
    val selectedChild by pagerComponent.selectedChild.collectAsState()
    val selectedBottomBarItem by pagerComponent.selectedBottomBarItem.collectAsState()
    Box(
        modifier = Modifier.fillMaxSize().navigationBarsPadding(),
    ) {
        Crossfade(
            modifier = modifier.fillMaxWidth(),
            targetState = selectedChild,
            label = "Crossfade instance composable"
        ) { instance ->
            when (instance) {
                is PagerComponent.Child.RatingUsers -> ratingUsersScreen.Render(
                    child = instance,
                    modifier = Modifier
                )

                is PagerComponent.Child.Status -> statusScreen.Render(
                    child = instance,
                    modifier = Modifier
                )

                is PagerComponent.Child.Towns -> townsScreen.Render(
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
            onClicked = pagerComponent::select,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
