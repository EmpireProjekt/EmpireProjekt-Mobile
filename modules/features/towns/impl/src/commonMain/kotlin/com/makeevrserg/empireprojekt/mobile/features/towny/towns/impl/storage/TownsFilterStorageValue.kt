package com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.storage

import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.TownsFilter
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.TownsFilter.TownPublicType
import com.makeevrserg.empireprojekt.mobile.services.core.model.LocalSortOrder
import com.makeevrserg.empireprojekt.mobile.services.core.settings.util.loadEnumByOrdinal
import com.makeevrserg.empireprojekt.mobile.services.core.settings.util.putEnumByOrdinal
import com.russhwolf.settings.Settings
import ru.astrainteractive.klibs.kstorage.api.CachedMutableKrate
import ru.astrainteractive.klibs.kstorage.api.asCachedMutableKrate
import ru.astrainteractive.klibs.kstorage.api.impl.DefaultMutableKrate

private class Keys(key: String) {
    val queryKey: String = "${key}queryKey"
    val publicTypeKey: String = "${key}publicTypeKey"
    val nameSortKey: String = "${key}nameSortKey"
    val tagSortKey: String = "${key}tagSortKey"
    val founderSortKey: String = "${key}founderSortKey"
    val nationSortKey: String = "${key}nationSortKey"
    val dateSortKey: String = "${key}dateSortKey"
    val residentsSortKey: String = "${key}residentsSortKey"
}

internal class TownsFilterStorageValue(
    settings: Settings,
    key: String,
    default: TownsFilter
) : CachedMutableKrate<TownsFilter> by DefaultMutableKrate(
    factory = { default },
    loader = {
        val keys = Keys(key)
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
    saver = {
        val keys = Keys(key)
        settings.putString(key = keys.queryKey, value = it.query)
        settings.putEnumByOrdinal(keys.publicTypeKey, it.publicType)
        settings.putEnumByOrdinal(keys.nameSortKey, it.nameSort)
        settings.putEnumByOrdinal(keys.tagSortKey, it.tagSort)
        settings.putEnumByOrdinal(keys.founderSortKey, it.founderSort)
        settings.putEnumByOrdinal(keys.nationSortKey, it.nationSort)
        settings.putEnumByOrdinal(keys.dateSortKey, it.dateSort)
        settings.putEnumByOrdinal(keys.residentsSortKey, it.residentsSort)
    }
).asCachedMutableKrate()
