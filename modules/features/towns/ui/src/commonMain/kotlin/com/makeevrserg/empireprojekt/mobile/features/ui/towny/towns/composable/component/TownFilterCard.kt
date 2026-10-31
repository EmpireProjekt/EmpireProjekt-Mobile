package com.makeevrserg.empireprojekt.mobile.features.ui.towny.towns.composable.component

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
import com.makeevrserg.empireprojekt.mobile.feature.towns.TR
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_filter_public_type_label
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_filter_sort_created_label
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_filter_sort_founder_label
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_filter_sort_name_label
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_filter_sort_nation_label
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_filter_sort_residents_label
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_filter_sort_tag_label
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.util.LocalSortOrderExt.toStringDesc
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.util.TownPublicTypeExt.toStringDesc
import ru.astrainteractive.empireapi.models.towny.LocalSortOrder
import ru.astrainteractive.empireapi.models.towny.TownsFilterModel
import ru.astrainteractive.empireapi.models.towny.TownsFilterModel.TownPublicType

@Composable
internal fun TownFilterCard(
    townsFilter: TownsFilterModel,
    onPublicTypeClick: () -> Unit,
    onSortByNameClick: () -> Unit,
    onSortByTagClick: () -> Unit,
    onSortByFounderClick: () -> Unit,
    onSortByNationClick: () -> Unit,
    onSortByDateClick: () -> Unit,
    onSortByResidentsClick: () -> Unit,
) {
    FilterCardComposable {
        SortFilterHeaderComposable()
        EnumOptionComposable(
            text = TR.strings.towns_filter_public_type_label.asComposableString(),
            selected = townsFilter.publicType,
            toString = { it.toStringDesc().asComposableString() },
            onClick = onPublicTypeClick
        )
        EnumOptionComposable(
            text = TR.strings.towns_filter_sort_name_label.asComposableString(),
            selected = townsFilter.nameSort,
            toString = { it.toStringDesc().asComposableString() },
            onClick = onSortByNameClick
        )
        EnumOptionComposable(
            text = TR.strings.towns_filter_sort_tag_label.asComposableString(),
            selected = townsFilter.tagSort,
            toString = { it.toStringDesc().asComposableString() },
            onClick = onSortByTagClick
        )
        EnumOptionComposable(
            text = TR.strings.towns_filter_sort_founder_label.asComposableString(),
            selected = townsFilter.founderSort,
            toString = { it.toStringDesc().asComposableString() },
            onClick = onSortByFounderClick
        )
        EnumOptionComposable(
            text = TR.strings.towns_filter_sort_nation_label.asComposableString(),
            selected = townsFilter.nationSort,
            toString = { it.toStringDesc().asComposableString() },
            onClick = onSortByNationClick
        )
        EnumOptionComposable(
            text = TR.strings.towns_filter_sort_created_label.asComposableString(),
            selected = townsFilter.dateSort,
            toString = { it.toStringDesc().asComposableString() },
            onClick = onSortByDateClick
        )
        EnumOptionComposable(
            text = TR.strings.towns_filter_sort_residents_label.asComposableString(),
            selected = townsFilter.residentsSort,
            toString = { it.toStringDesc().asComposableString() },
            onClick = onSortByResidentsClick
        )
    }
}

@Preview
@Composable
private fun TownFilterCardEmptyPreview() {
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.DARK) {
        Box(modifier = Modifier.background(MaterialTheme.colors.primaryVariant)) {
            TownFilterCard(
                townsFilter = TownsFilterModel(),
                onSortByNationClick = {},
                onSortByDateClick = {},
                onSortByNameClick = {},
                onPublicTypeClick = {},
                onSortByTagClick = {},
                onSortByFounderClick = {},
                onSortByResidentsClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun TownFilterCardSelectedPreview() {
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.DARK) {
        Box(modifier = Modifier.background(MaterialTheme.colors.primaryVariant)) {
            TownFilterCard(
                townsFilter = TownsFilterModel(
                    publicType = TownPublicType.PUBLIC,
                    tagSort = LocalSortOrder.ASC,
                    nationSort = LocalSortOrder.DESC
                ),
                onSortByNationClick = {},
                onSortByDateClick = {},
                onSortByNameClick = {},
                onPublicTypeClick = {},
                onSortByTagClick = {},
                onSortByFounderClick = {},
                onSortByResidentsClick = {}
            )
        }
    }
}
