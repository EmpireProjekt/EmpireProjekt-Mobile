package com.makeevrserg.empireprojekt.mobile.features.towny.towns.util

import com.makeevrserg.empireprojekt.mobile.feature.towns.TR
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_public_type_private_label
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_public_type_public_label
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_sort_order_none_label
import ru.astrainteractive.empireapi.models.towny.TownPublicType

object TownPublicTypeExt {
    fun TownPublicType.toStringDesc() = when (this) {
        TownPublicType.NONE -> TR.strings.towns_sort_order_none_label
        TownPublicType.PUBLIC -> TR.strings.towns_public_type_public_label
        TownPublicType.PRIVATE -> TR.strings.towns_public_type_private_label
    }
}
