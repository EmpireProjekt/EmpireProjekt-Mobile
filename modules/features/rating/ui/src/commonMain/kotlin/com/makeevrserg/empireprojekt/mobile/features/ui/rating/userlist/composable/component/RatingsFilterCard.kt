package com.makeevrserg.empireprojekt.mobile.features.ui.rating.userlist.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.filtercard.composable.EnumOptionComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.filtercard.composable.FilterCardComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.filtercard.composable.SortFilterHeaderComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.model.ComposeTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asComposableString
import com.makeevrserg.empireprojekt.mobile.features.rating.userlist.util.LocalSortOrderExt.toStringDesc
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
    FilterCardComposable {
        SortFilterHeaderComposable()
        EnumOptionComposable(
            text = RR.strings.rating_filter_sort_name_label.asComposableString(),
            selected = filter.nameSort,
            toString = { it.toStringDesc().asComposableString() },
            onClick = onNameSortClick
        )
        EnumOptionComposable(
            text = RR.strings.rating_filter_sort_last_update_label.asComposableString(),
            selected = filter.lastUpdatedSort,
            toString = { it.toStringDesc().asComposableString() },
            onClick = onLastUpdateSortClick
        )
        EnumOptionComposable(
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
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.DARK) {
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
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.DARK) {
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
