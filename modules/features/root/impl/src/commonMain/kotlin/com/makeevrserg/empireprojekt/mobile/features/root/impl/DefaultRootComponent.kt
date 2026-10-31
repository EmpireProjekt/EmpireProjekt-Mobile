package com.makeevrserg.empireprojekt.mobile.features.root.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.makeevrserg.empireprojekt.mobile.features.root.impl.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.root.impl.screen.DefaultRootScreenComponent

class DefaultRootComponent(
    componentContext: ComponentContext,
    rootModule: RootModule
) : RootComponent, ComponentContext by componentContext {
    override val rootScreenComponent = DefaultRootScreenComponent(
        componentContext = childContext("RootScreenComponent"),
        rootModule = rootModule,
    )
}
