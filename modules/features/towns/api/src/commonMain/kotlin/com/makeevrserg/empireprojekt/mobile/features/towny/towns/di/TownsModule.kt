package com.makeevrserg.empireprojekt.mobile.features.towny.towns.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.component.TownsComponent

interface TownsModule {
    fun createTownsComponent(componentContext: ComponentContext): TownsComponent
}
