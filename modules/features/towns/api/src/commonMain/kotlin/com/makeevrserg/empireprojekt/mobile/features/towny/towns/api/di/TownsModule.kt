package com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.component.TownsComponent

interface TownsModule {
    fun createTownsComponent(componentContext: ComponentContext): TownsComponent
}
