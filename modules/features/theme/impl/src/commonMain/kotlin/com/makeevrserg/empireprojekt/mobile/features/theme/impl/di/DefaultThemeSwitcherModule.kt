package com.makeevrserg.empireprojekt.mobile.features.theme.impl.di

import com.makeevrserg.empireprojekt.mobile.features.theme.api.component.ThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.api.di.ThemeSwitcherModule
import com.makeevrserg.empireprojekt.mobile.features.theme.impl.component.DefaultThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.impl.krate.KrateThemeSwitcherRepository
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule

class DefaultThemeSwitcherModule(coreModule: CoreModule) : ThemeSwitcherModule {
    override val themeSwitcherComponent: ThemeSwitcherComponent by lazy {
        DefaultThemeSwitcherComponent(
            themeSwitcherRepository = KrateThemeSwitcherRepository(
                settings = coreModule.settings
            )
        )
    }
}
