package com.makeevrserg.empireprojekt.mobile.features.rating.impl.userlist.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.rating.api.api.RatingRepository
import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingsFilter
import com.makeevrserg.empireprojekt.mobile.features.rating.api.userlist.component.RatingUsersComponent
import com.makeevrserg.empireprojekt.mobile.features.rating.api.userlist.di.RatingUsersModule
import com.makeevrserg.empireprojekt.mobile.features.rating.impl.userlist.component.DefaultRatingUsersComponent
import com.makeevrserg.empireprojekt.mobile.features.rating.impl.userlist.component.FilterFeature
import com.makeevrserg.empireprojekt.mobile.features.rating.impl.userlist.component.RatingUsersFeature
import com.makeevrserg.empireprojekt.mobile.features.rating.impl.userlist.krate.RatingsFilterStorageValue
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule

class DefaultRatingUsersModule(
    private val ratingRepository: RatingRepository,
    private val coreModule: CoreModule
) : RatingUsersModule {

    override fun createRatingUsersComponent(
        componentContext: ComponentContext,
        onShowUserRatingsCallback: RatingUsersComponent.OnShowUserRatingsCallback
    ): RatingUsersComponent {
        return DefaultRatingUsersComponent(
            componentContext = componentContext,
            onShowUserRatingsCallback = onShowUserRatingsCallback,
            createRatingUsersFeature = { filterProvider ->
                RatingUsersFeature(
                    ratingRepository = ratingRepository,
                    filterProvider = filterProvider,
                    dispatchers = coreModule.dispatchers
                )
            },
            createFilterFeature = {
                FilterFeature(
                    ratingsFilterStorageValue = RatingsFilterStorageValue(
                        settings = coreModule.settings,
                        key = "ratings_filter_storage_key",
                        default = RatingsFilter()
                    )
                )
            }
        )
    }
}
