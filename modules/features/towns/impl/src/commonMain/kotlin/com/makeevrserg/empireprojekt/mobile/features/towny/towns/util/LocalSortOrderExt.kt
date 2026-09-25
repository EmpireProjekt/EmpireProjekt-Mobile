package com.makeevrserg.empireprojekt.mobile.features.towny.towns.util

import com.makeevrserg.empireprojekt.mobile.feature.towns.TR
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_sort_order_ascending_label
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_sort_order_descending_label
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_sort_order_none_label
import ru.astrainteractive.empireapi.models.towny.LocalSortOrder

object LocalSortOrderExt {
    fun LocalSortOrder.toStringDesc() = when (this) {
        LocalSortOrder.NONE -> TR.strings.towns_sort_order_none_label
        LocalSortOrder.ASC -> TR.strings.towns_sort_order_ascending_label
        LocalSortOrder.DESC -> TR.strings.towns_sort_order_descending_label
    }
}
