package com.makeevrserg.empireprojekt.mobile.services.core.di.factory

import com.makeevrserg.empireprojekt.mobile.services.core.LinkBrowser
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

internal expect class LinkBrowserFactory(platformConfiguration: PlatformConfiguration) {
    fun create(): LinkBrowser
}
