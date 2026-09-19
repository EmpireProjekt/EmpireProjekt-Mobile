package com.makeevrserg.empireprojekt.mobile.features.rating.users.util

import com.makeevrserg.empireprojekt.mobile.rating.RR
import ru.astrainteractive.empireapi.models.towny.LocalSortOrder
import com.makeevrserg.empireprojekt.mobile.rating.rating_local_sort_asc
import com.makeevrserg.empireprojekt.mobile.rating.rating_local_sort_desc
import com.makeevrserg.empireprojekt.mobile.rating.rating_town_sort_by_none

object LocalSortOrderExt {
    fun LocalSortOrder.toStringDesc() = when (this) {
        LocalSortOrder.NONE -> RR.strings.rating_town_sort_by_none
        LocalSortOrder.ASC -> RR.strings.rating_local_sort_asc
        LocalSortOrder.DESC -> RR.strings.rating_local_sort_desc
    }
}
