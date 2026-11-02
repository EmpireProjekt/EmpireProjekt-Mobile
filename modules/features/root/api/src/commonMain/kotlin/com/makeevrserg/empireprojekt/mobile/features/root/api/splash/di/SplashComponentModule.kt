package com.makeevrserg.empireprojekt.mobile.features.root.api.splash.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.root.api.splash.component.SplashComponent

interface SplashComponentModule {
    fun createSplashComponent(componentContext: ComponentContext): SplashComponent
}
