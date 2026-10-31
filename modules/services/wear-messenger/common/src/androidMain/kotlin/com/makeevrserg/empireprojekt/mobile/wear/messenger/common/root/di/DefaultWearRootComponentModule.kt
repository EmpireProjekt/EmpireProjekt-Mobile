package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.root.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.theme.api.di.ThemeSwitcherModule
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.ping.di.PingModule
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.root.component.WearRootComponent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.root.di.WearRootComponentModule
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.root.model.WearRootConfiguration
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.status.di.WearStatusModule
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.root.component.DefaultWearRootComponent
import kotlinx.serialization.modules.SerializersModule

class DefaultWearRootComponentModule(
    private val serializersModule: SerializersModule,
    private val themeSwitcherModule: ThemeSwitcherModule,
    private val wearStatusModule: WearStatusModule,
    private val pingModule: PingModule
) : WearRootComponentModule {

    override fun createRootComponent(componentContext: ComponentContext): WearRootComponent {
        return DefaultWearRootComponent(
            componentContext = componentContext,
            serializersModule = serializersModule,
            wearRootComponentModule = this
        )
    }

    override fun createRootChild(
        configuration: WearRootConfiguration,
        componentContext: ComponentContext
    ): WearRootComponent.Child {
        return when (configuration) {
            WearRootConfiguration.Main -> WearRootComponent.Child.Main(
                themeSwitcherComponent = themeSwitcherModule.themeSwitcherComponent
            )

            WearRootConfiguration.Ping -> WearRootComponent.Child.Ping(
                pingComponent = pingModule.createPingComponent(componentContext)
            )

            WearRootConfiguration.Statuses -> WearRootComponent.Child.Statuses(
                wearStatusComponent = wearStatusModule.wearStatusComponent
            )
        }
    }
}
