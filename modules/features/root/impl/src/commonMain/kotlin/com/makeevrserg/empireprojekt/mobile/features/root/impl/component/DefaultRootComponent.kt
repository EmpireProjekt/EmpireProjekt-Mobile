package com.makeevrserg.empireprojekt.mobile.features.root.impl.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.makeevrserg.empireprojekt.mobile.features.root.api.component.RootComponent
import com.makeevrserg.empireprojekt.mobile.features.root.api.component.RootScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.root.api.di.RootComponentModule

internal class DefaultRootComponent(
    componentContext: ComponentContext,
    rootComponentModule: RootComponentModule
) : RootComponent, ComponentContext by componentContext {
    override val rootScreenComponent: RootScreenComponent = rootComponentModule.createRootScreenComponent(
        componentContext = childContext("RootScreenComponent")
    )
}
