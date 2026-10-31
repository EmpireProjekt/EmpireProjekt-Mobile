package com.makeevrserg.empireprojekt.mobile.features.rating.impl.userlist.component

import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingsFilter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.astrainteractive.klibs.kstorage.api.StateFlowMutableKrate
import ru.astrainteractive.klibs.mikro.core.coroutines.CoroutineFeature
import ru.astrainteractive.klibs.mikro.extensions.arkivanov.EssentyCoroutineFeature
import ru.astrainteractive.klibs.mikro.extensions.arkivanov.asEssentyCoroutineFeature

internal class FilterFeature(
    private val ratingsFilterStorageValue: StateFlowMutableKrate<RatingsFilter>
) : EssentyCoroutineFeature by CoroutineFeature.Main.asEssentyCoroutineFeature(),
    RatingUsersFeature.FilterProvider {
    private val _state = MutableStateFlow(ratingsFilterStorageValue.cachedValue.copy(query = ""))
    override val filterStateFlow = _state.asStateFlow()

    fun updateFilter(block: (RatingsFilter) -> RatingsFilter) {
        _state.update(block)
        ratingsFilterStorageValue.save(filterStateFlow.value.copy(query = ""))
    }
}
