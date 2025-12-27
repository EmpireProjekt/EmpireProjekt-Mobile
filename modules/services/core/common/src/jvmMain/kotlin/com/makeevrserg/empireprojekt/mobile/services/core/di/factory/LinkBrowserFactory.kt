package com.makeevrserg.empireprojekt.mobile.services.core.di.factory

import com.makeevrserg.empireprojekt.mobile.services.core.LinkBrowser
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

@Suppress("UnusedPrivateMember")
internal actual class LinkBrowserFactory actual constructor(
    private val platformConfiguration: PlatformConfiguration
) {
    override fun create(): LinkBrowser {
        return object : LinkBrowser {
            override fun openInBrowser(url: String) {
                TODO("Not yet implemented")
            }
        }
    }
}
