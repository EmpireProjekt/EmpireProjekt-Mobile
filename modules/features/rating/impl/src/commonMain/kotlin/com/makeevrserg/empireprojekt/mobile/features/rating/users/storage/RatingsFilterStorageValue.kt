package com.makeevrserg.empireprojekt.mobile.features.rating.users.storage

import com.makeevrserg.empireprojekt.mobile.services.core.settings.util.loadEnumByOrdinal
import com.makeevrserg.empireprojekt.mobile.services.core.settings.util.putEnumByOrdinal
import com.russhwolf.settings.Settings
import ru.astrainteractive.empireapi.models.rating.RatingsFilterModel
import ru.astrainteractive.empireapi.models.towny.LocalSortOrder
import ru.astrainteractive.klibs.kstorage.api.StateFlowMutableKrate
import ru.astrainteractive.klibs.kstorage.api.asStateFlowMutableKrate
import ru.astrainteractive.klibs.kstorage.api.impl.DefaultMutableKrate

private class Keys(key: String) {
    val queryKey: String = "${key}queryKey"
    val nameSortKey: String = "${key}nameSortKey"
    val lastUpdatedSortKey: String = "${key}lastUpdatedSortKey"
    val ratingSortKey: String = "${key}ratingSortKey"
}

internal class RatingsFilterStorageValue(
    settings: Settings,
    key: String,
    default: RatingsFilterModel
) : StateFlowMutableKrate<RatingsFilterModel> by DefaultMutableKrate(
    factory = { default },
    loader = {
        val keys = Keys(key)
        RatingsFilterModel(
            query = settings.getString(key = keys.queryKey, defaultValue = default.query),
            nameSort = settings.loadEnumByOrdinal(
                LocalSortOrder.entries,
                keys.nameSortKey,
                LocalSortOrder.NONE
            ),
            lastUpdatedSort = settings.loadEnumByOrdinal(
                LocalSortOrder.entries,
                keys.lastUpdatedSortKey,
                LocalSortOrder.NONE
            ),
            ratingSort = settings.loadEnumByOrdinal(
                LocalSortOrder.entries,
                keys.ratingSortKey,
                LocalSortOrder.NONE
            ),
        )
    },
    saver = { filter ->
        val keys = Keys(key)
        settings.putString(key = keys.queryKey, value = filter.query)
        settings.putEnumByOrdinal(keys.nameSortKey, filter.nameSort)
        settings.putEnumByOrdinal(keys.lastUpdatedSortKey, filter.lastUpdatedSort)
        settings.putEnumByOrdinal(keys.ratingSortKey, filter.ratingSort)
    }
).asStateFlowMutableKrate()
