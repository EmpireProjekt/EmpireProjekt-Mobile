package com.makeevrserg.empireprojekt.mobile.features.rating.users.util

import com.makeevrserg.empireprojekt.mobile.rating.RR
import com.makeevrserg.empireprojekt.mobile.rating.rating_sort_order_ascending_label
import com.makeevrserg.empireprojekt.mobile.rating.rating_sort_order_descending_label
import com.makeevrserg.empireprojekt.mobile.rating.rating_sort_order_none_label
import ru.astrainteractive.empireapi.models.towny.LocalSortOrder

object LocalSortOrderExt {
    fun LocalSortOrder.toStringDesc() = when (this) {
        LocalSortOrder.NONE -> RR.strings.rating_sort_order_none_label
        LocalSortOrder.ASC -> RR.strings.rating_sort_order_ascending_label
        LocalSortOrder.DESC -> RR.strings.rating_sort_order_descending_label
    }
}
