package com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.util

import com.makeevrserg.empireprojekt.mobile.feature.towns.TR
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_public_type_private_label
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_public_type_public_label
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_sort_order_none_label
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.TownsFilter.TownPublicType
import dev.icerock.moko.resources.StringResource

fun TownPublicType.toStringDesc(): StringResource {
    return when (this) {
        TownPublicType.NONE -> TR.strings.towns_sort_order_none_label
        TownPublicType.PUBLIC -> TR.strings.towns_public_type_public_label
        TownPublicType.PRIVATE -> TR.strings.towns_public_type_private_label
    }
}
