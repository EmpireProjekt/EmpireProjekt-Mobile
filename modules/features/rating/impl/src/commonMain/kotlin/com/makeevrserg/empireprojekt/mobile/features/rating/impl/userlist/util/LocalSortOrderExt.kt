package com.makeevrserg.empireprojekt.mobile.features.rating.impl.userlist.util

import com.makeevrserg.empireprojekt.mobile.rating.RR
import com.makeevrserg.empireprojekt.mobile.rating.rating_sort_order_ascending_label
import com.makeevrserg.empireprojekt.mobile.rating.rating_sort_order_descending_label
import com.makeevrserg.empireprojekt.mobile.rating.rating_sort_order_none_label
import com.makeevrserg.empireprojekt.mobile.services.core.model.LocalSortOrder
import dev.icerock.moko.resources.StringResource

fun LocalSortOrder.toStringDesc(): StringResource {
    return when (this) {
        LocalSortOrder.NONE -> RR.strings.rating_sort_order_none_label
        LocalSortOrder.ASC -> RR.strings.rating_sort_order_ascending_label
        LocalSortOrder.DESC -> RR.strings.rating_sort_order_descending_label
    }
}
