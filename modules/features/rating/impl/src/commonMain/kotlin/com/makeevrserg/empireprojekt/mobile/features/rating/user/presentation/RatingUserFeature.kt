package com.makeevrserg.empireprojekt.mobile.features.rating.user.presentation

import com.makeevrserg.empireprojekt.mobile.features.rating.user.data.RatingUserRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.astrainteractive.empireapi.models.rating.UserRatingsRequest
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers
import ru.astrainteractive.klibs.mikro.core.util.mapStateFlow
import ru.astrainteractive.klibs.mikro.extensions.arkivanov.CoroutineFeature
import ru.astrainteractive.klibs.paging.collector.IntPagerCollector
import ru.astrainteractive.klibs.paging.data.PagedListDataSource
import ru.astrainteractive.klibs.paging.state.isFailure
import ru.astrainteractive.klibs.paging.state.isLastPage
import ru.astrainteractive.klibs.paging.state.isLoading
import ru.astrainteractive.klibs.paging.util.loadNextPage
import ru.astrainteractive.klibs.paging.util.resetAndLoadNextPage

internal class RatingUserFeature(
    private val userId: Long,
    userName: String,
    private val ratingUserRepository: RatingUserRepository,
    private val dispatchers: KotlinDispatchers
) : CoroutineFeature by CoroutineFeature.Main() {
    private val pagingCollector = IntPagerCollector(
        pager = PagedListDataSource { pagingState ->
            withContext(dispatchers.IO) {
                runCatching {
                    ratingUserRepository.getRatings(
                        userId = userId,
                        page = pagingState.pageContext.page,
                        size = 10
                    )
                }
            }
        }
    )
    val model = pagingCollector.state.mapStateFlow {
        RatingUserComponent.Model(
            items = it.items,
            request = UserRatingsRequest(id = userId),
            reviewedUserName = userName,
            isLoading = it.pageResult.isLoading,
            isLastPage = it.pageResult.isLastPage,
            isFailure = it.pageResult.isFailure
        )
    }

    fun reset() = launch(dispatchers.IO) {
        pagingCollector.resetAndLoadNextPage()
    }

    fun loadNextPage() = launch(dispatchers.IO) {
        pagingCollector.loadNextPage()
    }

    init {
        reset()
    }
}
