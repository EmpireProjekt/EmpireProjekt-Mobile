package com.makeevrserg.empireprojekt.mobile.wear.feature.root.ui

import androidx.compose.runtime.Composable
import com.makeevrserg.empireprojekt.mobile.wear.feature.main.ui.MainScreen
import com.makeevrserg.empireprojekt.mobile.wear.feature.ping.ui.PingScreen
import com.makeevrserg.empireprojekt.mobile.wear.feature.root.component.RootComponent
import com.makeevrserg.empireprojekt.mobile.wear.feature.status.ui.StatusesScreen
import com.makeevrserg.empireprojekt.mobile.wear.ui.SwipeToDismissBox

@Composable
fun RootScreen(
    rootComponent: RootComponent,
) {
    SwipeToDismissBox(stack = rootComponent.childStack, onDismiss = rootComponent::pop) { child ->
        when (val instance = child.instance) {
            is RootComponent.Child.Main -> MainScreen(
                themeSwitcherComponent =
                instance.themeSwitcherComponent,
                onOpenStatusesClick = rootComponent::openStatuses,
                onOpenPingClick = rootComponent::openPing
            )

            is RootComponent.Child.Ping -> PingScreen(
                pingComponent = instance.pingComponent
            )

            is RootComponent.Child.Statuses -> StatusesScreen(
                wearStatusComponent = instance.wearStatusComponent
            )
        }
    }
}
