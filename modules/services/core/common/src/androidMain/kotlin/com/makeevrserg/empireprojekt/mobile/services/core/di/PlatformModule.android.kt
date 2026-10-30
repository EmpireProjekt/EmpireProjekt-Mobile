package com.makeevrserg.empireprojekt.mobile.services.core.di

import com.makeevrserg.empireprojekt.mobile.services.core.link.api.LinkBrowser
import com.makeevrserg.empireprojekt.mobile.services.core.link.util.openInBrowser
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

internal actual class PlatformModule actual constructor(platformConfiguration: PlatformConfiguration) {
    private val context = platformConfiguration.applicationContext

    actual val settings: Settings by lazy {
        SharedPreferencesSettings.Factory(context).create(SETTINGS_NAME)
    }

    actual val linkBrowser: LinkBrowser by lazy {
        LinkBrowser(context::openInBrowser)
    }

    private companion object {
        const val SETTINGS_NAME = "SETTINGS"
    }
}
