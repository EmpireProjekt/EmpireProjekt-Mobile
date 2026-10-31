package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.ping.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.api.WearMessageConsumer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.ping.component.PingComponent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.ping.di.PingModule
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.ping.component.DefaultPingComponent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.component.PingFeature
import kotlin.time.Clock

class DefaultPingModule(
    wearMessageConsumer: WearMessageConsumer,
    clock: Clock
) : PingModule {
    private val pingFeature: PingFeature by lazy {
        PingFeature(
            wearMessageConsumer = wearMessageConsumer,
            clock = clock
        )
    }

    override fun createPingComponent(componentContext: ComponentContext): PingComponent {
        return DefaultPingComponent(
            componentContext = componentContext,
            pingFeature = pingFeature
        )
    }
}
