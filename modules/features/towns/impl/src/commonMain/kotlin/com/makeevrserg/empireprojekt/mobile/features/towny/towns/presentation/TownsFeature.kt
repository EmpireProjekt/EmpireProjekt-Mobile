package com.makeevrserg.empireprojekt.mobile.features.towny.towns.presentation

import com.makeevrserg.empireprojekt.mobile.features.towny.towns.di.TownsDependencies
import kotlinx.coroutines.launch
import ru.astrainteractive.empireapi.models.towny.TownsFilterModel
import ru.astrainteractive.klibs.mikro.core.coroutines.CoroutineFeature
import ru.astrainteractive.klibs.mikro.core.util.mapStateFlow
import ru.astrainteractive.klibs.mikro.extensions.arkivanov.EssentyCoroutineFeature
import ru.astrainteractive.klibs.mikro.extensions.arkivanov.asEssentyCoroutineFeature
import ru.astrainteractive.klibs.paging.state.isFailure
import ru.astrainteractive.klibs.paging.state.isLastPage
import ru.astrainteractive.klibs.paging.state.isLoading
import ru.astrainteractive.klibs.paging.util.loadNextPage
import ru.astrainteractive.klibs.paging.util.resetAndLoadNextPage

internal class TownsFeature(
    dependencies: TownsDependencies
) : EssentyCoroutineFeature by CoroutineFeature.Main.asEssentyCoroutineFeature(),
    TownsDependencies by dependencies {

    fun loadNextPage() {
        launch { townsRepository.pagingCollector.loadNextPage() }
    }

    fun reset() {
        launch { townsRepository.pagingCollector.resetAndLoadNextPage() }
    }

    fun updateFilter(block: (TownsFilterModel) -> TownsFilterModel) = launch {
        townsRepository.updateFilter(block)
    }

    val model = townsRepository.pagingCollector.state.mapStateFlow {
        TownsComponent.Model(
            items = it.items,
            isLoading = it.pageResult.isLoading,
            isFailure = it.pageResult.isFailure,
            isLastPage = it.pageResult.isLastPage,
            filter = it.pageContext.filter
        )
    }
}
