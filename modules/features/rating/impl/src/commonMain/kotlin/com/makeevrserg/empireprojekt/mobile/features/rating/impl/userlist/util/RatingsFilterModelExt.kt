package com.makeevrserg.empireprojekt.mobile.features.rating.impl.userlist.util

import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingsFilter
import com.makeevrserg.empireprojekt.mobile.services.core.model.LocalSortOrder
import ru.astrainteractive.klibs.mikro.core.util.next

private fun LocalSortOrder?.next(): LocalSortOrder {
    return this?.next(LocalSortOrder.entries.toTypedArray()) ?: LocalSortOrder.entries.first()
}

internal fun RatingsFilter.updateQuery(query: String): RatingsFilter {
    return copy(query = query)
}

internal fun RatingsFilter.updateNextLastUpdatedSort(): RatingsFilter {
    return copy(lastUpdatedSort = lastUpdatedSort.next())
}

internal fun RatingsFilter.updateNextRatingSort(): RatingsFilter {
    return copy(ratingSort = ratingSort.next())
}

internal fun RatingsFilter.updateNextNameSort(): RatingsFilter {
    return copy(nameSort = nameSort.next())
}
