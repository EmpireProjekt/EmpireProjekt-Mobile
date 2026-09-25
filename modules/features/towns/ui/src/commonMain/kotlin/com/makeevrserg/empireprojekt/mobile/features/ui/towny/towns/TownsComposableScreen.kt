package com.makeevrserg.empireprojekt.mobile.features.ui.towny.towns

import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.appbar.AstraCenterAlignedTopAppBar
import com.makeevrserg.empireprojekt.mobile.core.ui.paging.PagingLazyColumn
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.ComposeTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asComposableString
import com.makeevrserg.empireprojekt.mobile.feature.towns.TR
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_list_title
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.presentation.TownsComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.towny.towns.components.TownCard
import com.makeevrserg.empireprojekt.mobile.features.ui.towny.towns.components.TownFilterCard
import com.makeevrserg.empireprojekt.mobile.features.ui.towny.towns.components.TownShimmerWidget
import ru.astrainteractive.empireapi.models.towny.TownModel
import ru.astrainteractive.empireapi.models.towny.TownsFilterModel

@Suppress("LongMethod", "LongParameterList")
@Composable
internal fun TownsComposableScreen(
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
            AstraCenterAlignedTopAppBar(
                title = TR.strings.towns_list_title.asComposableString(),
                onBackClick = onBack
            )
        }
    ) { contentPadding ->
        PagingLazyColumn(
            items = model.items,
            isLastPage = model.isLastPage,
            isLoading = model.isLoading,
            isFailure = model.isFailure,
            onLoadNextPage = onLoadNextPage,
            onReload = onReset,
            shimmerItem = { TownShimmerWidget() },
            contentPadding = contentPadding,
            header = {
                TownFilterCard(
                    townsFilter = model.filter,
                    onSortByNationClick = onSortByNationClick,
                    onSortByResidentsClick = onSortByResidentsClick,
                    onSortByTagClick = onSortByTagClick,
                    onSortByDateClick = onSortByDateClick,
                    onSortByNameClick = onSortByNameClick,
                    onPublicTypeClick = onPublicTypeClick,
                    onSortByFounderClick = onSortByFounderClick
                )
            },
            itemContent = { townModel ->
                TownCard(
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
        )
    }
}

@Suppress("MagicNumber")
private fun previewTownModel(
    name: String,
    mayor: String,
    nation: String,
    residentsCount: Long
): TownModel = TownModel(
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

private val previewTownItems: List<TownModel> = listOf(
    previewTownModel(name = "Rostov", mayor = "RomaRoman", nation = "NCR", residentsCount = 10),
    previewTownModel(name = "Moscow", mayor = "cinnamonrein", nation = "USSR", residentsCount = 42)
)

@Preview
@Composable
private fun TownsComposableScreenLoadedPreview() {
    AdaptThemeFade(composeTheme = ComposeTheme.DARK) {
        TownsComposableScreen(
            model = TownsComponent.Model(
                items = previewTownItems,
                filter = TownsFilterModel()
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
    AdaptThemeFade(composeTheme = ComposeTheme.DARK) {
        TownsComposableScreen(
            model = TownsComponent.Model(
                items = emptyList(),
                filter = TownsFilterModel(),
                isLoading = true
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
    AdaptThemeFade(composeTheme = ComposeTheme.DARK) {
        TownsComposableScreen(
            model = TownsComponent.Model(
                items = emptyList(),
                filter = TownsFilterModel(),
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
    AdaptThemeFade(composeTheme = ComposeTheme.DARK) {
        TownsComposableScreen(
            model = TownsComponent.Model(
                items = previewTownItems,
                filter = TownsFilterModel(),
                isFailure = true
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
