package com.makeevrserg.empireprojekt.mobile.features.ui.towny.towns.composable.screen

import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.appbar.composable.AstraCenterAlignedTopAppBarComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.paging.composable.PagingLazyColumnComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.paging.composable.PagingListFooterComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.paging.model.PagingContent
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.model.ComposeTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asComposableString
import com.makeevrserg.empireprojekt.mobile.feature.towns.TR
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_list_title
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.component.TownsComponent
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.Town
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.TownsFilter
import com.makeevrserg.empireprojekt.mobile.features.ui.towny.towns.composable.component.TownCardComposable
import com.makeevrserg.empireprojekt.mobile.features.ui.towny.towns.composable.component.TownFilterCardComposable
import com.makeevrserg.empireprojekt.mobile.features.ui.towny.towns.composable.component.TownShimmerWidgetComposable

@Suppress("LongMethod", "LongParameterList")
@Composable
internal fun TownsScreenComposable(
    model: TownsComponent.Model,
    onBack: (() -> Unit)?,
    onLoadNextPage: () -> Unit,
    onReset: () -> Unit,
    onPublicTypeClick: () -> Unit,
    onSortByNameClick: () -> Unit,
    onSortByTagClick: () -> Unit,
    onSortByFounderClick: () -> Unit,
    onSortByNationClick: () -> Unit,
    onSortByDateClick: () -> Unit,
    onSortByResidentsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AstraCenterAlignedTopAppBarComposable(
                title = TR.strings.towns_list_title.asComposableString(),
                onBackClick = onBack
            )
        }
    ) { contentPadding ->
        PagingLazyColumnComposable(
            onLoadNextPage = onLoadNextPage,
            contentPadding = contentPadding
        ) {
            item {
                TownFilterCardComposable(
                    townsFilter = model.filter,
                    onSortByNationClick = onSortByNationClick,
                    onSortByResidentsClick = onSortByResidentsClick,
                    onSortByTagClick = onSortByTagClick,
                    onSortByDateClick = onSortByDateClick,
                    onSortByNameClick = onSortByNameClick,
                    onPublicTypeClick = onPublicTypeClick,
                    onSortByFounderClick = onSortByFounderClick
                )
            }
            items(model.items) { townModel ->
                TownCardComposable(
                    mayor = townModel.mayor,
                    townName = townModel.name,
                    board = townModel.townBoard,
                    founder = townModel.founder,
                    nation = townModel.nation,
                    outlawsAmount = townModel.outlaws.size,
                    tag = townModel.tag,
                    registered = townModel.registered,
                    residentsCount = townModel.residentsCount,
                    isOpen = townModel.open
                )
            }
            item {
                PagingListFooterComposable(
                    paging = PagingContent(
                        isEmpty = model.items.isEmpty(),
                        isLastPage = model.isLastPage,
                        isLoading = model.isLoading,
                        isFailure = model.isFailure
                    ),
                    onReload = onReset,
                    shimmerItem = { TownShimmerWidgetComposable() }
                )
            }
        }
    }
}

@Suppress("MagicNumber")
private fun previewTownModel(
    name: String,
    mayor: String,
    nation: String,
    residentsCount: Long
): Town {
    return Town(
        name = name,
        mayor = mayor,
        nation = nation,
        townBoard = "Welcome to $name",
        tag = name.take(3).uppercase(),
        founder = mayor,
        hasUpkeep = true,
        open = true,
        public = true,
        outlaws = listOf("griefer1"),
        registered = 1706549308031,
        ruined = false,
        residentsCount = residentsCount
    )
}

private val PreviewTownItems: List<Town> = listOf(
    previewTownModel(
        name = "Rostov",
        mayor = "RomaRoman",
        nation = "NCR",
        residentsCount = 10
    ),
    previewTownModel(
        name = "Moscow",
        mayor = "cinnamonrein",
        nation = "USSR",
        residentsCount = 42
    )
)

@Preview
@Composable
private fun TownsComposableScreenLoadedPreview() {
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.DARK) {
        TownsScreenComposable(
            model = TownsComponent.Model(
                items = PreviewTownItems,
                filter = TownsFilter(),
                isLoading = false,
                isFailure = false,
                isLastPage = false
            ),
            onBack = {},
            onLoadNextPage = {},
            onReset = {},
            onPublicTypeClick = {},
            onSortByNameClick = {},
            onSortByTagClick = {},
            onSortByFounderClick = {},
            onSortByNationClick = {},
            onSortByDateClick = {},
            onSortByResidentsClick = {}
        )
    }
}

@Preview
@Composable
private fun TownsComposableScreenLoadingPreview() {
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.DARK) {
        TownsScreenComposable(
            model = TownsComponent.Model(
                items = emptyList(),
                filter = TownsFilter(),
                isLoading = true,
                isFailure = false,
                isLastPage = false
            ),
            onBack = {},
            onLoadNextPage = {},
            onReset = {},
            onPublicTypeClick = {},
            onSortByNameClick = {},
            onSortByTagClick = {},
            onSortByFounderClick = {},
            onSortByNationClick = {},
            onSortByDateClick = {},
            onSortByResidentsClick = {}
        )
    }
}

@Preview
@Composable
private fun TownsComposableScreenEmptyPreview() {
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.DARK) {
        TownsScreenComposable(
            model = TownsComponent.Model(
                items = emptyList(),
                filter = TownsFilter(),
                isLoading = false,
                isFailure = false,
                isLastPage = true
            ),
            onBack = {},
            onLoadNextPage = {},
            onReset = {},
            onPublicTypeClick = {},
            onSortByNameClick = {},
            onSortByTagClick = {},
            onSortByFounderClick = {},
            onSortByNationClick = {},
            onSortByDateClick = {},
            onSortByResidentsClick = {}
        )
    }
}

@Preview
@Composable
private fun TownsComposableScreenFailurePreview() {
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.DARK) {
        TownsScreenComposable(
            model = TownsComponent.Model(
                items = PreviewTownItems,
                filter = TownsFilter(),
                isLoading = false,
                isFailure = true,
                isLastPage = false
            ),
            onBack = {},
            onLoadNextPage = {},
            onReset = {},
            onPublicTypeClick = {},
            onSortByNameClick = {},
            onSortByTagClick = {},
            onSortByFounderClick = {},
            onSortByNationClick = {},
            onSortByDateClick = {},
            onSortByResidentsClick = {}
        )
    }
}
