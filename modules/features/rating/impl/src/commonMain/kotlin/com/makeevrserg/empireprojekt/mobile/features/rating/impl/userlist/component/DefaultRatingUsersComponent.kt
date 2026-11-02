package com.makeevrserg.empireprojekt.mobile.features.rating.impl.userlist.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingUserId
import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingsFilter
import com.makeevrserg.empireprojekt.mobile.features.rating.api.userlist.component.RatingUsersComponent
import com.makeevrserg.empireprojekt.mobile.features.rating.api.userlist.model.RatingUsersState
import com.makeevrserg.empireprojekt.mobile.features.rating.impl.userlist.util.updateNextLastUpdatedSort
import com.makeevrserg.empireprojekt.mobile.features.rating.impl.userlist.util.updateNextNameSort
import com.makeevrserg.empireprojekt.mobile.features.rating.impl.userlist.util.updateNextRatingSort
import com.makeevrserg.empireprojekt.mobile.features.rating.impl.userlist.util.updateQuery
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.klibs.mikro.core.util.combineStates
import ru.astrainteractive.klibs.paging.state.isFailure
import ru.astrainteractive.klibs.paging.state.isLastPage
import ru.astrainteractive.klibs.paging.state.isLoading

internal class DefaultRatingUsersComponent(
    componentContext: ComponentContext,
    private val onShowUserRatingsCallback: RatingUsersComponent.OnShowUserRatingsCallback,
    createRatingUsersFeature: (RatingUsersFeature.FilterProvider) -> RatingUsersFeature,
    createFilterFeature: () -> FilterFeature
) : RatingUsersComponent,
    ComponentContext by componentContext {
    private val filterFeature = instanceKeeper.getOrCreate {
        createFilterFeature.invoke()
    }
    private val ratingUsersFeature = instanceKeeper.getOrCreate {
        createRatingUsersFeature.invoke(filterFeature)
    }
    override val model: StateFlow<RatingUsersState> = combineStates(
        flow1 = filterFeature.filterStateFlow,
        flow2 = ratingUsersFeature.state,
        transform = { filter, pagingState ->
            RatingUsersState(
                items = pagingState.items,
                filter = filter,
                isLoading = pagingState.pageResult.isLoading,
                isFailure = pagingState.pageResult.isFailure,
                isLastPage = pagingState.pageResult.isLastPage
            )
        }
    )

    override fun reset() {
        ratingUsersFeature.reset()
    }

    override fun loadNextPage() {
        ratingUsersFeature.loadNextPage()
    }

    override fun updateQuery(query: String) {
        filterFeature.updateFilter { filter -> filter.updateQuery(query) }
    }

    override fun nextLastUpdateSort() {
        filterFeature.updateFilter(RatingsFilter::updateNextLastUpdatedSort)
    }

    override fun nextRatingSort() {
        filterFeature.updateFilter(RatingsFilter::updateNextRatingSort)
    }

    override fun nextNameSort() {
        filterFeature.updateFilter(RatingsFilter::updateNextNameSort)
    }

    override fun showUserRatings(id: RatingUserId, userName: String) {
        onShowUserRatingsCallback.invoke(id, userName)
    }
}
