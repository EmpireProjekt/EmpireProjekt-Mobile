package com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.component

import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.component.TownsComponent
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.Town
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.TownsFilter
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.model.TownyPageContext
import kotlinx.coroutines.launch
import ru.astrainteractive.klibs.kstorage.api.CachedMutableKrate
import ru.astrainteractive.klibs.mikro.core.coroutines.CoroutineFeature
import ru.astrainteractive.klibs.mikro.core.util.mapStateFlow
import ru.astrainteractive.klibs.mikro.extensions.arkivanov.EssentyCoroutineFeature
import ru.astrainteractive.klibs.mikro.extensions.arkivanov.asEssentyCoroutineFeature
import ru.astrainteractive.klibs.paging.collector.PagingCollector
import ru.astrainteractive.klibs.paging.state.isFailure
import ru.astrainteractive.klibs.paging.state.isLastPage
import ru.astrainteractive.klibs.paging.state.isLoading
import ru.astrainteractive.klibs.paging.util.loadNextPage
import ru.astrainteractive.klibs.paging.util.resetAndLoadNextPage

internal class TownsFeature(
    private val pagingCollector: PagingCollector<Town, TownyPageContext>,
    private val townsFilterStorageValue: CachedMutableKrate<TownsFilter>
) : EssentyCoroutineFeature by CoroutineFeature.Main.asEssentyCoroutineFeature() {

    val model = pagingCollector.state.mapStateFlow { pagingState ->
        TownsComponent.Model(
            items = pagingState.items,
            isLoading = pagingState.pageResult.isLoading,
            isFailure = pagingState.pageResult.isFailure,
            isLastPage = pagingState.pageResult.isLastPage,
            filter = pagingState.pageContext.filter
        )
    }

    fun loadNextPage() {
        launch { pagingCollector.loadNextPage() }
    }

    fun reset() {
        launch { pagingCollector.resetAndLoadNextPage() }
    }

    fun updateFilter(block: (TownsFilter) -> TownsFilter) {
        launch {
            val newFilter = block.invoke(pagingCollector.state.value.pageContext.filter)
            townsFilterStorageValue.save(newFilter)
            pagingCollector.resetAndLoadNextPage()
        }
    }
}
