package com.makeevrserg.empireprojekt.mobile.wear

import android.app.Application
import android.content.Context
import com.makeevrserg.empireprojekt.mobile.wear.di.WearRootModule
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.api.WearMessageConsumer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.api.WearMessengerApplication
import ru.astrainteractive.klibs.mikro.platform.DefaultAndroidPlatformConfiguration

class App : Application(), WearMessengerApplication {
    internal val wearRootModule by lazy {
        WearRootModule()
    }

    override val wearMessageConsumer: WearMessageConsumer
        get() = wearRootModule.wearMessengerModule.wearMessageConsumer

    override fun onCreate() {
        super.onCreate()
        wearRootModule.coreModule.platformConfiguration = DefaultAndroidPlatformConfiguration(
            applicationContext
        )
    }

    companion object {
        fun Application.asEmpireApp(): App = (this as App)
        fun Context.asEmpireApp(): App = (applicationContext as App)
    }
}
