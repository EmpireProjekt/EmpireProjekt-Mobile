package com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.data

import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.Town
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.TownsFilter
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.data.paging.TownyPagingCollector

internal interface TownsRepository {
    val pagingCollector: TownyPagingCollector<Town>
    suspend fun updateFilter(buildFilter: (TownsFilter) -> TownsFilter)
}
