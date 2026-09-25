package com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.filtercard.EnumOption
import com.makeevrserg.empireprojekt.mobile.core.ui.filtercard.FilterCard
import com.makeevrserg.empireprojekt.mobile.core.ui.filtercard.SortFilterHeader
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.ComposeTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asComposableString
import com.makeevrserg.empireprojekt.mobile.features.rating.users.util.LocalSortOrderExt.toStringDesc
import com.makeevrserg.empireprojekt.mobile.rating.RR
import com.makeevrserg.empireprojekt.mobile.rating.rating_filter_sort_last_update_label
import com.makeevrserg.empireprojekt.mobile.rating.rating_filter_sort_name_label
import com.makeevrserg.empireprojekt.mobile.rating.rating_filter_sort_rating_label
import ru.astrainteractive.empireapi.models.rating.RatingsFilterModel
import ru.astrainteractive.empireapi.models.towny.LocalSortOrder

@Composable
internal fun RatingsFilterCard(
    filter: RatingsFilterModel,
    onNameSortClick: () -> Unit,
    onLastUpdateSortClick: () -> Unit,
    onRatingSortClick: () -> Unit,
) {
    FilterCard {
        SortFilterHeader()
        EnumOption(
            text = RR.strings.rating_filter_sort_name_label.asComposableString(),
            selected = filter.nameSort,
            toString = { it.toStringDesc().asComposableString() },
            onClick = onNameSortClick
        )
        EnumOption(
            text = RR.strings.rating_filter_sort_last_update_label.asComposableString(),
            selected = filter.lastUpdatedSort,
            toString = { it.toStringDesc().asComposableString() },
            onClick = onLastUpdateSortClick
        )
        EnumOption(
            text = RR.strings.rating_filter_sort_rating_label.asComposableString(),
            selected = filter.ratingSort,
            toString = { it.toStringDesc().asComposableString() },
            onClick = onRatingSortClick
        )
    }
}

@Preview
@Composable
private fun RatingsFilterCardEmptyPreview() {
    AdaptThemeFade(composeTheme = ComposeTheme.DARK) {
        Box(modifier = Modifier.background(MaterialTheme.colors.primaryVariant)) {
            RatingsFilterCard(
                filter = RatingsFilterModel(),
                onLastUpdateSortClick = {},
                onNameSortClick = {},
                onRatingSortClick = {},
            )
        }
    }
}

@Preview
@Composable
private fun RatingsFilterCardSelectedPreview() {
    AdaptThemeFade(composeTheme = ComposeTheme.DARK) {
        Box(modifier = Modifier.background(MaterialTheme.colors.primaryVariant)) {
            RatingsFilterCard(
                filter = RatingsFilterModel(
                    nameSort = LocalSortOrder.ASC,
                    lastUpdatedSort = LocalSortOrder.DESC
                ),
                onLastUpdateSortClick = {},
                onNameSortClick = {},
                onRatingSortClick = {},
            )
        }
    }
}
