package com.makeevrserg.empireprojekt.mobile.wear.feature.ping.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.wear.feature.ping.component.DefaultPingComponent
import com.makeevrserg.empireprojekt.mobile.wear.feature.ping.component.PingComponent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.datalayer.di.WearMessengerModule
import com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.component.PingFeature
import kotlin.time.Clock

interface PingModule {
    fun createPingComponent(componentContext: ComponentContext): PingComponent
    class Default(wearMessengerModule: WearMessengerModule) : PingModule {
        private val pingFeature: PingFeature by lazy {
            PingFeature(
                wearMessageConsumer = wearMessengerModule.wearMessageConsumer,
                clock = Clock.System
            )
        }

        override fun createPingComponent(componentContext: ComponentContext): PingComponent {
            return DefaultPingComponent(
                componentContext = componentContext,
                pingFeature = pingFeature
            )
        }
    }
}
