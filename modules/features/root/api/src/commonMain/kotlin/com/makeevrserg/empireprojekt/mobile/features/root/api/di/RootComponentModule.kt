package com.makeevrserg.empireprojekt.mobile.features.root.api.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.root.api.api.RootRouter
import com.makeevrserg.empireprojekt.mobile.features.root.api.component.RootComponent
import com.makeevrserg.empireprojekt.mobile.features.root.api.component.RootScreenComponent

interface RootComponentModule {
    fun createRootComponent(componentContext: ComponentContext): RootComponent

    fun createRootScreenComponent(componentContext: ComponentContext): RootScreenComponent

    fun createRootScreenChild(
        configuration: RootRouter.Configuration,
        componentContext: ComponentContext,
        rootRouter: RootRouter
    ): RootScreenComponent.ScreenChild
}
