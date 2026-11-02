package com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.api

import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.Town
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.TownsFilter
import com.makeevrserg.empireprojekt.mobile.services.core.model.PagedData

interface TownRepository {
    suspend fun towns(page: Int, size: Int, filter: TownsFilter): PagedData<Town>
}
