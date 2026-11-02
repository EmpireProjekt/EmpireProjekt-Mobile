package com.makeevrserg.empireprojekt.mobile.features.splash.impl.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.root.api.splash.component.SplashComponent
import com.makeevrserg.empireprojekt.mobile.features.root.api.splash.di.SplashComponentModule
import com.makeevrserg.empireprojekt.mobile.features.splash.impl.component.DefaultSplashComponent
import com.makeevrserg.empireprojekt.mobile.features.splash.impl.memory.InMemorySplashComponentRepository
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule

class DefaultSplashComponentModule(
    private val coreModule: CoreModule
) : SplashComponentModule {
    override fun createSplashComponent(componentContext: ComponentContext): SplashComponent {
        return DefaultSplashComponent(
            context = componentContext,
            mainScope = coreModule.mainScope,
            dispatchers = coreModule.dispatchers,
            repository = InMemorySplashComponentRepository()
        )
    }
}
