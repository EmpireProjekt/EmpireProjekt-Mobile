package com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.api

import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.Town
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.TownsFilter
import com.makeevrserg.empireprojekt.mobile.services.core.model.GenericPagedModel

interface TownRepository {
    suspend fun towns(page: Int, size: Int, filter: TownsFilter): GenericPagedModel<Town>
}
