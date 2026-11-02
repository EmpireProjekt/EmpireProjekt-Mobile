package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.root.composable.screen

import androidx.compose.runtime.Composable
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.root.component.WearRootComponent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.composable.layout.SwipeToDismissBoxComposable
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.main.composable.screen.MainScreenHostComposable
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.ping.composable.screen.PingScreenHostComposable
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.status.composable.screen.StatusesScreenHostComposable

@Composable
private fun MainChildComposable(
    child: WearRootComponent.Child.Main,
    rootComponent: WearRootComponent
) {
    MainScreenHostComposable(
        themeSwitcherComponent = child.themeSwitcherComponent,
        onOpenStatusesClick = rootComponent::openStatuses,
        onOpenPingClick = rootComponent::openPing
    )
}

@Composable
fun WearRootScreenComposable(rootComponent: WearRootComponent) {
    SwipeToDismissBoxComposable(
        stack = rootComponent.childStack,
        onDismiss = rootComponent::pop
    ) { child ->
        when (val instance = child.instance) {
            is WearRootComponent.Child.Main -> MainChildComposable(instance, rootComponent)
            is WearRootComponent.Child.Ping -> PingScreenHostComposable(instance.pingComponent)
            is WearRootComponent.Child.Statuses -> StatusesScreenHostComposable(instance.wearStatusComponent)
        }
    }
}
