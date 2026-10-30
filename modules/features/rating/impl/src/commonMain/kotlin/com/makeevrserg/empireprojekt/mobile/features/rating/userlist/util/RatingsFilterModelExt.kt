package com.makeevrserg.empireprojekt.mobile.features.rating.userlist.util

import ru.astrainteractive.empireapi.models.rating.RatingsFilterModel
import ru.astrainteractive.empireapi.models.towny.LocalSortOrder
import ru.astrainteractive.klibs.mikro.core.util.next

private fun LocalSortOrder?.next(): LocalSortOrder {
    return this?.next(LocalSortOrder.entries.toTypedArray()) ?: LocalSortOrder.entries.first()
}

internal fun RatingsFilterModel.updateQuery(query: String): RatingsFilterModel {
    return copy(query = query)
}

internal fun RatingsFilterModel.updateNextLastUpdatedSort(): RatingsFilterModel {
    return copy(lastUpdatedSort = lastUpdatedSort.next())
}

internal fun RatingsFilterModel.updateNextRatingSort(): RatingsFilterModel {
    return copy(ratingSort = ratingSort.next())
}

internal fun RatingsFilterModel.updateNextNameSort(): RatingsFilterModel {
    return copy(nameSort = nameSort.next())
}
