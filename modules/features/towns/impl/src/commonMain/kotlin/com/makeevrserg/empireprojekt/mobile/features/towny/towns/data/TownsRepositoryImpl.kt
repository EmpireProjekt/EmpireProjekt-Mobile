package com.makeevrserg.empireprojekt.mobile.features.towny.towns.data

import com.makeevrserg.empireprojekt.mobile.api.empireapi.TownyApi
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.data.paging.TownyPagingCollector
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.storage.TownsFilterStorageValue
import com.russhwolf.settings.Settings
import kotlinx.coroutines.withContext
import ru.astrainteractive.empireapi.models.towny.TownsFilterModel
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers
import ru.astrainteractive.klibs.paging.data.PagedListDataSource
import ru.astrainteractive.klibs.paging.util.resetAndLoadNextPage

internal class TownsRepositoryImpl(
    private val townyApi: TownyApi,
    private val dispatchers: KotlinDispatchers,
    private val settings: Settings
) : TownsRepository {
    private val townsFilterStorageValue = TownsFilterStorageValue(
        settings = settings,
        key = "towns_filter_storage_key"
    )

    override val pagingCollector = TownyPagingCollector(
        initialPage = 0,
        pager = PagedListDataSource { pagingState ->
            withContext(dispatchers.IO) {
                runCatching {
                    townyApi.towns(
                        page = pagingState.pageContext.page,
                        size = 10,
                        filter = pagingState.pageContext.filter
                    ).data
                }
            }
        },
        initialFilterFactory = { townsFilterStorageValue.cachedValue }
    )

    override suspend fun updateFilter(buildFilter: (TownsFilterModel) -> TownsFilterModel) {
        val newFilter = buildFilter.invoke(pagingCollector.state.value.pageContext.filter)
        townsFilterStorageValue.save(newFilter)
        pagingCollector.resetAndLoadNextPage()
    }
}
