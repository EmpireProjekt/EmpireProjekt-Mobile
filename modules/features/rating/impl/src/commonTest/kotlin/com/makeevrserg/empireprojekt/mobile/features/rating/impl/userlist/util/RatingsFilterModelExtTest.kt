@file:Suppress("FunctionNaming")

package com.makeevrserg.empireprojekt.mobile.features.rating.impl.userlist.util

import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingsFilter
import com.makeevrserg.empireprojekt.mobile.services.core.model.LocalSortOrder
import kotlin.test.Test
import kotlin.test.assertEquals

class RatingsFilterModelExtTest {
    private val filter = RatingsFilter(
        nameSort = LocalSortOrder.NONE,
        lastUpdatedSort = LocalSortOrder.ASC,
        ratingSort = LocalSortOrder.DESC,
        query = "steve"
    )

    @Test
    fun GIVEN_filter_WHEN_query_is_updated_THEN_only_query_changes() {
        val actual = filter.updateQuery("alex")

        assertEquals(filter.copy(query = "alex"), actual)
    }

    @Test
    fun GIVEN_none_name_sort_WHEN_next_name_sort_is_updated_THEN_name_sort_is_ascending_and_rest_is_kept() {
        val actual = filter.updateNextNameSort()

        assertEquals(filter.copy(nameSort = LocalSortOrder.ASC), actual)
    }

    @Test
    fun GIVEN_ascending_last_updated_sort_WHEN_next_last_updated_sort_is_updated_THEN_it_is_descending() {
        val actual = filter.updateNextLastUpdatedSort()

        assertEquals(filter.copy(lastUpdatedSort = LocalSortOrder.DESC), actual)
    }

    @Test
    fun GIVEN_descending_rating_sort_WHEN_next_rating_sort_is_updated_THEN_it_wraps_to_none() {
        val actual = filter.updateNextRatingSort()

        assertEquals(filter.copy(ratingSort = LocalSortOrder.NONE), actual)
    }
}
