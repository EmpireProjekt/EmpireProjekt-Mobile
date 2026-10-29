package com.makeevrserg.empireprojekt.mobile.features.rating.users.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.empireprojekt.mobile.features.rating.model.RatingUserId
import com.makeevrserg.empireprojekt.mobile.features.rating.users.component.RatingUsersComponent.Model
import com.makeevrserg.empireprojekt.mobile.features.rating.users.util.updateNextLastUpdatedSort
import com.makeevrserg.empireprojekt.mobile.features.rating.users.util.updateNextNameSort
import com.makeevrserg.empireprojekt.mobile.features.rating.users.util.updateNextRatingSort
import com.makeevrserg.empireprojekt.mobile.features.rating.users.util.updateQuery
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.empireapi.models.rating.RatingsFilterModel
import ru.astrainteractive.klibs.mikro.core.util.combineStates
import ru.astrainteractive.klibs.paging.state.isFailure
import ru.astrainteractive.klibs.paging.state.isLastPage
import ru.astrainteractive.klibs.paging.state.isLoading

internal class DefaultRatingUsersComponent(
    componentContext: ComponentContext,
    private val onShowUserRatingsClicked: (userId: RatingUserId, userName: String) -> Unit,
    private val createRatingUsersFeature: (RatingUsersFeature.FilterProvider) -> RatingUsersFeature,
    private val createFilterFeature: () -> FilterFeature
) : RatingUsersComponent,
    ComponentContext by componentContext {
    private val filterFeature = instanceKeeper.getOrCreate {
        createFilterFeature.invoke()
    }
    private val ratingUsersFeature = instanceKeeper.getOrCreate {
        createRatingUsersFeature.invoke(filterFeature)
    }
    override val model: StateFlow<Model> = combineStates(
        flow1 = filterFeature.filterStateFlow,
        flow2 = ratingUsersFeature.state,
        transform = { filter, pagingState ->
            Model(
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
        filterFeature.updateFilter(RatingsFilterModel::updateNextLastUpdatedSort)
    }

    override fun nextRatingSort() {
        filterFeature.updateFilter(RatingsFilterModel::updateNextRatingSort)
    }

    override fun nextNameSort() {
        filterFeature.updateFilter(RatingsFilterModel::updateNextNameSort)
    }

    override fun showUserRatings(id: RatingUserId, userName: String) {
        onShowUserRatingsClicked.invoke(id, userName)
    }
}
