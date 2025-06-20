package com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.ui.filtercard.EnumOption
import com.makeevrserg.empireprojekt.mobile.core.ui.filtercard.FilterCard
import com.makeevrserg.empireprojekt.mobile.core.ui.filtercard.TextOption
import com.makeevrserg.empireprojekt.mobile.core.ui.filtercard.TitleOption
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.ComposeTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asComposableString
import com.makeevrserg.empireprojekt.mobile.features.rating.users.util.LocalSortOrderExt.toStringDesc
import com.makeevrserg.empireprojekt.mobile.rating.RR
import ru.astrainteractive.empireapi.models.rating.RatingsFilterModel
import ru.astrainteractive.empireapi.models.towny.LocalSortOrder

@Composable
internal fun RatingsFilterCard(
    filter: RatingsFilterModel,
    onNameSortClicked: () -> Unit,
    onLastUpdateSortClicked: () -> Unit,
    onRatingSortClicked: () -> Unit,
) {
    FilterCard {
        TitleOption(text = MR.strings.shared_filter.asComposableString())
        TextOption(text = MR.strings.shared_warn_multiple_filter.asComposableString())
        EnumOption(
            text = RR.strings.rating_ratings_filter_name.asComposableString(),
            selected = filter.nameSort,
            toString = { it.toStringDesc().asComposableString() },
            onClicked = onNameSortClicked
        )
        EnumOption(
            text = RR.strings.rating_ratings_filter_last_update.asComposableString(),
            selected = filter.lastUpdatedSort,
            toString = { it.toStringDesc().asComposableString() },
            onClicked = onLastUpdateSortClicked
        )
        EnumOption(
            text = RR.strings.rating_ratings_filter_rating.asComposableString(),
            selected = filter.ratingSort,
            toString = { it.toStringDesc().asComposableString() },
            onClicked = onRatingSortClicked
        )
    }
}

@Preview
@Composable
private fun TownFilterCardPreview() {
    AdaptThemeFade(composeTheme = ComposeTheme.DARK) {
        Box(modifier = Modifier.background(MaterialTheme.colors.primaryVariant)) {
            RatingsFilterCard(
                filter = RatingsFilterModel(
                    nameSort = LocalSortOrder.ASC,
                    lastUpdatedSort = LocalSortOrder.DESC
                ),
                onLastUpdateSortClicked = {},
                onNameSortClicked = {},
                onRatingSortClicked = {},
            )
        }
    }
}
