package com.makeevrserg.empireprojekt.mobile.services.core.di

import com.makeevrserg.empireprojekt.mobile.services.core.link.api.LinkBrowser
import com.russhwolf.settings.Settings
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

internal expect class PlatformModule(platformConfiguration: PlatformConfiguration) {
    val settings: Settings
    val linkBrowser: LinkBrowser
}
