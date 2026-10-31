package com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.api.TownRepository
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.component.TownsComponent
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.di.TownsModule
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.TownsFilter
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.component.DefaultTownsComponent
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.component.TownsFeature
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.krate.TownsFilterStorageValue
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.paging.TownyPagingCollector
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule
import kotlinx.coroutines.withContext
import ru.astrainteractive.klibs.mikro.core.coroutines.propagateCancellationException
import ru.astrainteractive.klibs.paging.data.PagedListDataSource

class DefaultTownsModule(
    private val townRepository: TownRepository,
    private val coreModule: CoreModule
) : TownsModule {
    private fun createTownsFeature(): TownsFeature {
        val townsFilterStorageValue = TownsFilterStorageValue(
            settings = coreModule.settings,
            key = "towns_filter_storage_key",
            default = TownsFilter()
        )
        val pagingCollector = TownyPagingCollector(
            initialPage = 0,
            pager = PagedListDataSource { pagingState ->
                withContext(coreModule.dispatchers.IO) {
                    runCatching {
                        townRepository.towns(
                            page = pagingState.pageContext.page,
                            size = 10,
                            filter = pagingState.pageContext.filter
                        ).data
                    }.propagateCancellationException()
                }
            },
            initialFilterFactory = { townsFilterStorageValue.cachedValue }
        )
        return TownsFeature(
            pagingCollector = pagingCollector,
            townsFilterStorageValue = townsFilterStorageValue
        )
    }

    override fun createTownsComponent(componentContext: ComponentContext): TownsComponent {
        return DefaultTownsComponent(
            componentContext = componentContext,
            createTownsFeature = ::createTownsFeature
        )
    }
}
