package com.makeevrserg.empireprojekt.mobile.wear.features.root.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.makeevrserg.empireprojekt.mobile.features.theme.component.ThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.wear.features.ping.component.PingComponent
import com.makeevrserg.empireprojekt.mobile.wear.features.status.component.WearStatusComponent

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>

    fun openStatuses()

    fun openPing()

    fun pop()

    sealed interface Child {
        class Ping(val pingComponent: PingComponent) : Child
        class Main(val themeSwitcherComponent: ThemeSwitcherComponent) : Child
        class Statuses(val wearStatusComponent: WearStatusComponent) : Child
    }
}
