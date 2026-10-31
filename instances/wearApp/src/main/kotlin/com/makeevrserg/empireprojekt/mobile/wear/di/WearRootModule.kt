package com.makeevrserg.empireprojekt.mobile.wear.di

import com.makeevrserg.empireprojekt.mobile.features.theme.api.di.ThemeSwitcherModule
import com.makeevrserg.empireprojekt.mobile.features.theme.impl.di.ThemeSwitcherModuleImpl
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule
import com.makeevrserg.empireprojekt.mobile.wear.feature.ping.di.PingModule
import com.makeevrserg.empireprojekt.mobile.wear.feature.status.component.DefaultWearStatusComponent
import com.makeevrserg.empireprojekt.mobile.wear.feature.status.component.WearStatusComponent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.datalayer.di.WearMessengerModule

interface WearRootModule {
    val coreModule: CoreModule
    val themeSwitcherModule: ThemeSwitcherModule
    val wearStatusComponent: WearStatusComponent
    val wearMessengerModule: WearMessengerModule
    val pingModule: PingModule

    class Default : WearRootModule {
        override val coreModule: CoreModule.Default by lazy {
            CoreModule.Default()
        }
        override val themeSwitcherModule: ThemeSwitcherModule by lazy {
            ThemeSwitcherModuleImpl(coreModule = coreModule)
        }

        override val wearMessengerModule: WearMessengerModule by lazy {
            WearMessengerModule.Default(
                context = coreModule.platformConfiguration.applicationContext,
                coroutineScope = coreModule.mainScope,
            )
        }

        override val wearStatusComponent by lazy {
            DefaultWearStatusComponent(
                wearMessageConsumer = wearMessengerModule.wearMessageConsumer,
                coroutineScope = coreModule.mainScope
            )
        }

        override val pingModule: PingModule by lazy {
            PingModule.Default(
                wearMessengerModule = wearMessengerModule
            )
        }
    }
}
