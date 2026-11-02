package com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.krate

import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.TownsFilter
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.TownsFilter.TownPublicType
import com.makeevrserg.empireprojekt.mobile.services.core.model.LocalSortOrder
import com.makeevrserg.empireprojekt.mobile.services.core.settings.util.loadEnumByOrdinal
import com.makeevrserg.empireprojekt.mobile.services.core.settings.util.putEnumByOrdinal
import com.russhwolf.settings.Settings
import ru.astrainteractive.klibs.kstorage.api.CachedMutableKrate
import ru.astrainteractive.klibs.kstorage.api.asCachedMutableKrate
import ru.astrainteractive.klibs.kstorage.api.impl.DefaultMutableKrate

internal class TownsFilterStorageValue(
    settings: Settings,
    key: String,
    default: TownsFilter
) : CachedMutableKrate<TownsFilter> by DefaultMutableKrate(
    factory = { default },
    loader = {
        val keys = TownsFilterKeys(key)
        TownsFilter(
            query = settings.getString(key = keys.queryKey, defaultValue = default.query),
            publicType = settings.loadEnumByOrdinal(
                TownPublicType.entries,
                keys.publicTypeKey,
                TownPublicType.NONE
            ),
            nameSort = settings.loadEnumByOrdinal(
                LocalSortOrder.entries,
                keys.nameSortKey,
                LocalSortOrder.NONE
            ),
            tagSort = settings.loadEnumByOrdinal(
                LocalSortOrder.entries,
                keys.tagSortKey,
                LocalSortOrder.NONE
            ),
            founderSort = settings.loadEnumByOrdinal(
                LocalSortOrder.entries,
                keys.founderSortKey,
                LocalSortOrder.NONE
            ),
            nationSort = settings.loadEnumByOrdinal(
                LocalSortOrder.entries,
                keys.nationSortKey,
                LocalSortOrder.NONE
            ),
            dateSort = settings.loadEnumByOrdinal(
                LocalSortOrder.entries,
                keys.dateSortKey,
                LocalSortOrder.NONE
            ),
            residentsSort = settings.loadEnumByOrdinal(
                LocalSortOrder.entries,
                keys.residentsSortKey,
                LocalSortOrder.NONE
            ),
        )
    },
    saver = { filter ->
        val keys = TownsFilterKeys(key)
        settings.putString(key = keys.queryKey, value = filter.query)
        settings.putEnumByOrdinal(keys.publicTypeKey, filter.publicType)
        settings.putEnumByOrdinal(keys.nameSortKey, filter.nameSort)
        settings.putEnumByOrdinal(keys.tagSortKey, filter.tagSort)
        settings.putEnumByOrdinal(keys.founderSortKey, filter.founderSort)
        settings.putEnumByOrdinal(keys.nationSortKey, filter.nationSort)
        settings.putEnumByOrdinal(keys.dateSortKey, filter.dateSort)
        settings.putEnumByOrdinal(keys.residentsSortKey, filter.residentsSort)
    }
).asCachedMutableKrate()
