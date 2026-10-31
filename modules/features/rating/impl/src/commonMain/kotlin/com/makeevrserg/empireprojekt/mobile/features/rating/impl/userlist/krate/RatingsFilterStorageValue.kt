package com.makeevrserg.empireprojekt.mobile.features.rating.impl.userlist.krate

import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingsFilter
import com.makeevrserg.empireprojekt.mobile.services.core.model.LocalSortOrder
import com.makeevrserg.empireprojekt.mobile.services.core.settings.util.loadEnumByOrdinal
import com.makeevrserg.empireprojekt.mobile.services.core.settings.util.putEnumByOrdinal
import com.russhwolf.settings.Settings
import ru.astrainteractive.klibs.kstorage.api.StateFlowMutableKrate
import ru.astrainteractive.klibs.kstorage.api.asStateFlowMutableKrate
import ru.astrainteractive.klibs.kstorage.api.impl.DefaultMutableKrate

internal class RatingsFilterStorageValue(
    settings: Settings,
    key: String,
    default: RatingsFilter
) : StateFlowMutableKrate<RatingsFilter> by DefaultMutableKrate(
    factory = { default },
    loader = {
        val keys = RatingsFilterKeys(key)
        RatingsFilter(
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
        val keys = RatingsFilterKeys(key)
        settings.putString(key = keys.queryKey, value = filter.query)
        settings.putEnumByOrdinal(keys.nameSortKey, filter.nameSort)
        settings.putEnumByOrdinal(keys.lastUpdatedSortKey, filter.lastUpdatedSort)
        settings.putEnumByOrdinal(keys.ratingSortKey, filter.ratingSort)
    }
).asStateFlowMutableKrate()
