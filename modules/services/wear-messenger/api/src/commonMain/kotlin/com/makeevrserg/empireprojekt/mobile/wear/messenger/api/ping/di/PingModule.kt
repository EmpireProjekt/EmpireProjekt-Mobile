package com.makeevrserg.empireprojekt.mobile.wear.messenger.api.ping.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.ping.component.PingComponent

interface PingModule {
    fun createPingComponent(componentContext: ComponentContext): PingComponent
}
