package com.makeevrserg.empireprojekt.mobile.services.core.di.factory

import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

@Suppress("UnusedPrivateMember")
internal actual class SettingsFactory actual constructor(
    private val configuration: PlatformConfiguration
) {
    actual fun create(): Settings {
        return SharedPreferencesSettings.Factory(configuration.applicationContext).create("SETTINGS")
    }
}
