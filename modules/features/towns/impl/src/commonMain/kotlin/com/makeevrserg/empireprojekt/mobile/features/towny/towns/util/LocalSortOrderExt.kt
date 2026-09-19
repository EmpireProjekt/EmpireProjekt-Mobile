package com.makeevrserg.empireprojekt.mobile.features.towny.towns.util

import com.makeevrserg.empireprojekt.mobile.feature.towns.TR
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_local_sort_asc
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_local_sort_desc
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_town_sort_by_none
import ru.astrainteractive.empireapi.models.towny.LocalSortOrder

object LocalSortOrderExt {
    fun LocalSortOrder.toStringDesc() = when (this) {
        LocalSortOrder.NONE -> TR.strings.towns_town_sort_by_none
        LocalSortOrder.ASC -> TR.strings.towns_local_sort_asc
        LocalSortOrder.DESC -> TR.strings.towns_local_sort_desc
    }
}
