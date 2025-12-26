package com.makeevrserg.empireprojekt.mobile.services.core.di.factory

import com.makeevrserg.empireprojekt.mobile.services.core.AndroidLinkBrowser
import com.makeevrserg.empireprojekt.mobile.services.core.LinkBrowser
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

internal actual class LinkBrowserFactory actual constructor(
    private val platformConfiguration: PlatformConfiguration
) {
    actual fun create(): LinkBrowser {
        return AndroidLinkBrowser(platformConfiguration.applicationContext)
    }
}
