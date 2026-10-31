package com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.data

import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.api.TownRepository
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.TownsFilter
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.data.paging.TownyPagingCollector
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.storage.TownsFilterStorageValue
import com.russhwolf.settings.Settings
import kotlinx.coroutines.withContext
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers
import ru.astrainteractive.klibs.paging.data.PagedListDataSource
import ru.astrainteractive.klibs.paging.util.resetAndLoadNextPage

internal class TownsRepositoryImpl(
    private val townRepository: TownRepository,
    private val dispatchers: KotlinDispatchers,
    private val settings: Settings
) : TownsRepository {
    private val townsFilterStorageValue = TownsFilterStorageValue(
        settings = settings,
        key = "towns_filter_storage_key",
        default = TownsFilter()
    )

    override val pagingCollector = TownyPagingCollector(
        initialPage = 0,
        pager = PagedListDataSource { pagingState ->
            withContext(dispatchers.IO) {
                runCatching {
                    townRepository.towns(
                        page = pagingState.pageContext.page,
                        size = 10,
                        filter = pagingState.pageContext.filter
                    ).data
                }
            }
        },
        initialFilterFactory = { townsFilterStorageValue.cachedValue }
    )

    override suspend fun updateFilter(buildFilter: (TownsFilter) -> TownsFilter) {
        val newFilter = buildFilter.invoke(pagingCollector.state.value.pageContext.filter)
        townsFilterStorageValue.save(newFilter)
        pagingCollector.resetAndLoadNextPage()
    }
}
