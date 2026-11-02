package com.makeevrserg.empireprojekt.mobile.wear.messenger.api.root.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.root.component.WearRootComponent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.root.model.WearRootConfiguration

interface WearRootComponentModule {
    fun createRootComponent(componentContext: ComponentContext): WearRootComponent

    fun createRootChild(
        configuration: WearRootConfiguration,
        componentContext: ComponentContext
    ): WearRootComponent.Child
}
