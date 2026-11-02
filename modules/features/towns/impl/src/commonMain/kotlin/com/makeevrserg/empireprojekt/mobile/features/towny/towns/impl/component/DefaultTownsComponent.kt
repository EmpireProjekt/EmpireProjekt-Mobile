package com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.component.TownsComponent
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.TownsFilter.TownPublicType
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.TownsState
import com.makeevrserg.empireprojekt.mobile.services.core.model.LocalSortOrder
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.klibs.mikro.core.util.next

@Suppress("TooManyFunctions")
internal class DefaultTownsComponent(
    componentContext: ComponentContext,
    createTownsFeature: () -> TownsFeature
) : TownsComponent, ComponentContext by componentContext {
    private val townsFeature = instanceKeeper.getOrCreate {
        createTownsFeature.invoke()
    }
    override val model: StateFlow<TownsState>
        get() = townsFeature.model

    private fun LocalSortOrder?.next(): LocalSortOrder {
        return this?.next(LocalSortOrder.entries.toTypedArray()) ?: LocalSortOrder.entries.first()
    }

    private fun TownPublicType?.next(): TownPublicType {
        return this?.next(TownPublicType.entries.toTypedArray()) ?: TownPublicType.entries.first()
    }

    override fun reset() {
        townsFeature.reset()
    }

    override fun loadNextPage() {
        townsFeature.loadNextPage()
    }

    override fun updateQuery(query: String) {
        townsFeature.updateFilter { filter -> filter.copy(query = query) }
    }

    override fun nextPublicType() {
        townsFeature.updateFilter { filter -> filter.copy(publicType = filter.publicType.next()) }
    }

    override fun nextNameSort() {
        townsFeature.updateFilter { filter -> filter.copy(nameSort = filter.nameSort.next()) }
    }

    override fun nextTagSort() {
        townsFeature.updateFilter { filter -> filter.copy(tagSort = filter.tagSort.next()) }
    }

    override fun nextFounderSort() {
        townsFeature.updateFilter { filter -> filter.copy(founderSort = filter.founderSort.next()) }
    }

    override fun nextNationSort() {
        townsFeature.updateFilter { filter -> filter.copy(nationSort = filter.nationSort.next()) }
    }

    override fun nextDateSort() {
        townsFeature.updateFilter { filter -> filter.copy(dateSort = filter.dateSort.next()) }
    }

    override fun nextResidentsSort() {
        townsFeature.updateFilter { filter -> filter.copy(residentsSort = filter.residentsSort.next()) }
    }
}
