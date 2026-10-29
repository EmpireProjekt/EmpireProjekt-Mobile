package com.makeevrserg.empireprojekt.mobile.wear.features.ping.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.wear.features.ping.component.DefaultPingComponent
import com.makeevrserg.empireprojekt.mobile.wear.features.ping.component.PingComponent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.di.WearMessengerModule
import com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.component.PingFeature

interface PingModule {
    fun createPingComponent(componentContext: ComponentContext): PingComponent
    class Default(wearMessengerModule: WearMessengerModule) : PingModule {
        private val pingFeature: PingFeature by lazy {
            PingFeature(
                wearMessageConsumer = wearMessengerModule.wearMessageConsumer,
                wearMessageProducer = wearMessengerModule.wearMessageProducer
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
