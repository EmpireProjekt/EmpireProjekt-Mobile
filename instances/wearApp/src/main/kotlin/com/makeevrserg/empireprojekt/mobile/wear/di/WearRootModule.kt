package com.makeevrserg.empireprojekt.mobile.wear.di

import com.makeevrserg.empireprojekt.mobile.features.theme.api.di.ThemeSwitcherModule
import com.makeevrserg.empireprojekt.mobile.features.theme.impl.di.DefaultThemeSwitcherModule
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.ping.di.PingModule
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.root.di.WearRootComponentModule
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.ping.di.DefaultPingModule
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.root.di.DefaultWearRootComponentModule
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.status.di.DefaultWearStatusModule
import com.makeevrserg.empireprojekt.mobile.wear.messenger.datalayer.di.WearMessengerModule
import kotlin.time.Clock

internal class WearRootModule {
    val coreModule: CoreModule.Default by lazy {
        CoreModule.Default()
    }

    val themeSwitcherModule: ThemeSwitcherModule by lazy {
        DefaultThemeSwitcherModule(coreModule = coreModule)
    }

    val wearMessengerModule: WearMessengerModule by lazy {
        WearMessengerModule.Default(
            context = coreModule.platformConfiguration.applicationContext,
            coroutineScope = coreModule.mainScope
        )
    }

    val wearStatusModule: DefaultWearStatusModule by lazy {
        DefaultWearStatusModule(
            wearMessageConsumer = wearMessengerModule.wearMessageConsumer,
            coroutineScope = coreModule.mainScope,
            clock = Clock.System,
            context = coreModule.platformConfiguration.applicationContext
        )
    }

    private val pingModule: PingModule by lazy {
        DefaultPingModule(
            wearMessageConsumer = wearMessengerModule.wearMessageConsumer,
            clock = Clock.System
        )
    }

    val wearRootComponentModule: WearRootComponentModule by lazy {
        DefaultWearRootComponentModule(
            serializersModule = coreModule.jsonConfiguration.serializersModule,
            themeSwitcherModule = themeSwitcherModule,
            wearStatusModule = wearStatusModule,
            pingModule = pingModule
        )
    }
}
