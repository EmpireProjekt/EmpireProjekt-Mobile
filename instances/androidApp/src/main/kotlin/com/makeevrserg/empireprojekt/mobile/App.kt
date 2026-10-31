package com.makeevrserg.empireprojekt.mobile

import android.app.Application
import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.initialize
import com.makeevrserg.empireprojekt.mobile.di.RootModule
import com.makeevrserg.empireprojekt.mobile.wear.messenger.datalayer.di.WearMessengerModule
import kotlinx.coroutines.cancel
import ru.astrainteractive.klibs.mikro.platform.DefaultAndroidPlatformConfiguration

class App : Application() {
    internal val rootModule by lazy {
        RootModule()
    }
    val wearMessengerModule by lazy {
        WearMessengerModule.Default(
            context = rootModule.coreModule.platformConfiguration.applicationContext,
            coroutineScope = rootModule.coreModule.mainScope,
        )
    }

    override fun onTerminate() {
        super.onTerminate()
        rootModule.coreModule.mainScope.cancel()
    }

    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)
        rootModule.coreModule.platformConfiguration = DefaultAndroidPlatformConfiguration(
            applicationContext
        )
    }

    companion object {
        fun Application.asEmpireApp(): App = (this as App)
        fun Context.asEmpireApp(): App = (applicationContext as App)
    }
}
