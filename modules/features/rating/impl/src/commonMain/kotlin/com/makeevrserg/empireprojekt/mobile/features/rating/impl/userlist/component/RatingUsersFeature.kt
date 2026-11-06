package com.makeevrserg.empireprojekt.mobile.features.rating.impl.userlist.component

import com.makeevrserg.empireprojekt.mobile.core.log.mikro.DefaultLogger
import com.makeevrserg.empireprojekt.mobile.features.rating.api.api.RatingRepository
import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingsFilter
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.astrainteractive.klibs.mikro.core.coroutines.CoroutineFeature
import ru.astrainteractive.klibs.mikro.core.coroutines.propagateCancellationException
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers
import ru.astrainteractive.klibs.mikro.extensions.arkivanov.EssentyCoroutineFeature
import ru.astrainteractive.klibs.mikro.extensions.arkivanov.asEssentyCoroutineFeature
import ru.astrainteractive.klibs.paging.collector.IntPagerCollector
import ru.astrainteractive.klibs.paging.data.PagedListDataSource
import ru.astrainteractive.klibs.paging.util.loadNextPage
import ru.astrainteractive.klibs.paging.util.resetAndLoadNextPage
import kotlin.time.Duration.Companion.milliseconds

internal class RatingUsersFeature(
    ratingRepository: RatingRepository,
    private val filterProvider: FilterProvider,
    private val dispatchers: KotlinDispatchers
) : EssentyCoroutineFeature by CoroutineFeature.Main.asEssentyCoroutineFeature() {

    private val logger = DefaultLogger("RatingUsersFeature")

    private val pagingCollector = IntPagerCollector(
        initialPage = 0,
        pager = PagedListDataSource { pagingState ->
            withContext(dispatchers.IO) {
                runCatching {
                    ratingRepository.users(
                        page = pagingState.pageContext.page,
                        size = 10,
                        body = filterProvider.filterStateFlow.value
                    ).data
                }.propagateCancellationException()
                    .onFailure { error ->
                        logger.error(error) { "Failed to load rating users page ${pagingState.pageContext.page}" }
                    }
            }
        },
    )

    val state = pagingCollector.state

    fun loadNextPage() {
        launch(dispatchers.IO) { pagingCollector.loadNextPage() }
    }

    fun reset() {
        launch(dispatchers.IO) { pagingCollector.resetAndLoadNextPage() }
    }

    init {
        filterProvider.filterStateFlow
            .debounce(300.milliseconds)
            .flowOn(dispatchers.IO)
            .onEach {
                pagingCollector.resetAndLoadNextPage()
            }.launchIn(this)
    }

    interface FilterProvider {
        val filterStateFlow: StateFlow<RatingsFilter>
    }
}
