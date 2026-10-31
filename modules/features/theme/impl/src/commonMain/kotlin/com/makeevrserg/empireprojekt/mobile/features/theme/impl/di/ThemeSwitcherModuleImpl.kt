package com.makeevrserg.empireprojekt.mobile.features.theme.impl.di

import com.makeevrserg.empireprojekt.mobile.features.theme.api.component.ThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.api.di.ThemeSwitcherModule
import com.makeevrserg.empireprojekt.mobile.features.theme.impl.component.DefaultThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.impl.data.ThemeSwitcherRepositoryImpl
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule

class ThemeSwitcherModuleImpl(coreModule: CoreModule) : ThemeSwitcherModule {
    override val themeSwitcherComponent: ThemeSwitcherComponent by lazy {
        DefaultThemeSwitcherComponent(
            themeSwitcherRepository = ThemeSwitcherRepositoryImpl(
                settings = coreModule.settings
            )
        )
    }
}
