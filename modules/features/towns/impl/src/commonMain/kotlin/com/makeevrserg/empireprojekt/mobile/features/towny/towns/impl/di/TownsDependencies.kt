package com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.di

import com.makeevrserg.empireprojekt.mobile.api.empireapi.di.EmpireApiModule
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.data.TownsRepository
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.data.TownsRepositoryImpl
import com.russhwolf.settings.Settings
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

internal interface TownsDependencies {
    val townsRepository: TownsRepository

    class Default(
        empireApiModule: EmpireApiModule,
        dispatchers: KotlinDispatchers,
        settings: Settings
    ) : TownsDependencies {
        override val townsRepository: TownsRepository by lazy {
            TownsRepositoryImpl(
                townRepository = empireApiModule.townRepository,
                dispatchers = dispatchers,
                settings = settings
            )
        }
    }
}
