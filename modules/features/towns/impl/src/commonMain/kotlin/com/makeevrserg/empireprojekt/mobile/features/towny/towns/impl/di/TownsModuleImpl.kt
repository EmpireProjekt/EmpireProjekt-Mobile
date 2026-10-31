package com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.api.empireapi.di.EmpireApiModule
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.component.TownsComponent
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.di.TownsModule
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.component.DefaultTownsComponent
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule

class TownsModuleImpl(
    private val empireApiModule: EmpireApiModule,
    private val coreModule: CoreModule
) : TownsModule {
    override fun createTownsComponent(componentContext: ComponentContext): TownsComponent {
        val dependencies = TownsDependencies.Default(
            empireApiModule = empireApiModule,
            dispatchers = coreModule.dispatchers,
            settings = coreModule.settings
        )
        return DefaultTownsComponent(
            componentContext = componentContext,
            dependencies = dependencies
        )
    }
}
