package com.makeevrserg.empireprojekt.mobile.features.rating.impl.user.component

import com.makeevrserg.empireprojekt.mobile.features.rating.api.api.RatingRepository
import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingUserId
import com.makeevrserg.empireprojekt.mobile.features.rating.api.user.component.RatingUserComponent
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.astrainteractive.klibs.mikro.core.coroutines.CoroutineFeature
import ru.astrainteractive.klibs.mikro.core.coroutines.propagateCancellationException
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers
import ru.astrainteractive.klibs.mikro.core.util.mapStateFlow
import ru.astrainteractive.klibs.mikro.extensions.arkivanov.EssentyCoroutineFeature
import ru.astrainteractive.klibs.mikro.extensions.arkivanov.asEssentyCoroutineFeature
import ru.astrainteractive.klibs.paging.collector.IntPagerCollector
import ru.astrainteractive.klibs.paging.data.PagedListDataSource
import ru.astrainteractive.klibs.paging.state.isFailure
import ru.astrainteractive.klibs.paging.state.isLastPage
import ru.astrainteractive.klibs.paging.state.isLoading
import ru.astrainteractive.klibs.paging.util.loadNextPage
import ru.astrainteractive.klibs.paging.util.resetAndLoadNextPage

internal class RatingUserFeature(
    userId: RatingUserId,
    userName: String,
    ratingRepository: RatingRepository,
    private val dispatchers: KotlinDispatchers
) : EssentyCoroutineFeature by CoroutineFeature.Main.asEssentyCoroutineFeature() {
    private val pagingCollector = IntPagerCollector(
        pager = PagedListDataSource { pagingState ->
            withContext(dispatchers.IO) {
                runCatching {
                    ratingRepository.ratings(
                        page = pagingState.pageContext.page,
                        size = 10,
                        userId = userId.value
                    ).data
                }.propagateCancellationException()
            }
        }
    )
    val model = pagingCollector.state.mapStateFlow { pagingState ->
        RatingUserComponent.Model(
            items = pagingState.items,
            reviewedUserName = userName,
            isLoading = pagingState.pageResult.isLoading,
            isLastPage = pagingState.pageResult.isLastPage,
            isFailure = pagingState.pageResult.isFailure
        )
    }

    fun reset() {
        launch(dispatchers.IO) {
            pagingCollector.resetAndLoadNextPage()
        }
    }

    fun loadNextPage() {
        launch(dispatchers.IO) {
            pagingCollector.loadNextPage()
        }
    }

    init {
        reset()
    }
}
