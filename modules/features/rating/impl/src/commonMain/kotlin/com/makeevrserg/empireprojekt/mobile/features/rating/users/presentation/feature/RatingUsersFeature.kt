package com.makeevrserg.empireprojekt.mobile.features.rating.users.presentation.feature

import com.makeevrserg.empireprojekt.mobile.features.rating.users.di.RatingUsersDependencies
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.astrainteractive.empireapi.models.rating.RatingsFilterModel
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers
import ru.astrainteractive.klibs.mikro.extensions.arkivanov.CoroutineFeature
import ru.astrainteractive.klibs.paging.collector.IntPagerCollector
import ru.astrainteractive.klibs.paging.data.PagedListDataSource
import ru.astrainteractive.klibs.paging.util.loadNextPage
import ru.astrainteractive.klibs.paging.util.resetAndLoadNextPage
import kotlin.time.Duration.Companion.milliseconds

internal class RatingUsersFeature(
    dependencies: RatingUsersDependencies,
    private val filterProvider: FilterProvider,
    private val dispatchers: KotlinDispatchers
) : CoroutineFeature by CoroutineFeature.Main(),
    RatingUsersDependencies by dependencies {

    private val pagingCollector = IntPagerCollector(
        initialPage = 0,
        pager = PagedListDataSource { pagingState ->
            withContext(dispatchers.IO) {
                runCatching {
                    ratingUsersRepository.fetchUsers(
                        page = pagingState.pageContext.page,
                        filterModel = filterProvider.filterStateFlow.value
                    ).getOrThrow().data
                }
            }
        },
    )

    fun loadNextPage() {
        launch(dispatchers.IO) { pagingCollector.loadNextPage() }
    }

    fun reset() {
        launch(dispatchers.IO) { pagingCollector.resetAndLoadNextPage() }
    }

    val state = pagingCollector.state

    init {
        filterProvider.filterStateFlow
            .debounce(300.milliseconds)
            .flowOn(dispatchers.IO)
            .onEach {
                pagingCollector.resetAndLoadNextPage()
            }.launchIn(this)
    }

    interface FilterProvider {
        val filterStateFlow: StateFlow<RatingsFilterModel>
    }
}
