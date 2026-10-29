package com.makeevrserg.empireprojekt.mobile.features.rating.users.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.api.empireapi.di.ApiEmpireApiModule
import com.makeevrserg.empireprojekt.mobile.features.rating.users.component.DefaultRatingUsersComponent
import com.makeevrserg.empireprojekt.mobile.features.rating.users.component.FilterFeature
import com.makeevrserg.empireprojekt.mobile.features.rating.users.component.RatingUsersComponent
import com.makeevrserg.empireprojekt.mobile.features.rating.users.component.RatingUsersFeature
import com.makeevrserg.empireprojekt.mobile.features.rating.users.data.RatingUsersRepository
import com.makeevrserg.empireprojekt.mobile.features.rating.users.data.RatingUsersRepositoryImpl
import com.makeevrserg.empireprojekt.mobile.features.rating.users.storage.RatingsFilterStorageValue
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule

class RatingUsersModuleImpl(
    apiEmpireApiModule: ApiEmpireApiModule,
    private val coreModule: CoreModule
) : RatingUsersModule {
    private val ratingUsersRepository: RatingUsersRepository by lazy {
        RatingUsersRepositoryImpl(
            ratingApi = apiEmpireApiModule.ratingApi,
            dispatchers = coreModule.dispatchers,
        )
    }

    override fun createRatingUsersComponent(
        componentContext: ComponentContext,
        onShowUserRatingsClicked: (userId: Long, userName: String) -> Unit
    ): RatingUsersComponent {
        val dependencies = RatingUsersDependencies.Default(
            ratingUsersRepository = ratingUsersRepository
        )
        return DefaultRatingUsersComponent(
            componentContext = componentContext,
            onShowUserRatingsClicked = onShowUserRatingsClicked,
            createRatingUsersFeature = { filterProvider ->
                RatingUsersFeature(
                    dependencies = dependencies,
                    filterProvider = filterProvider,
                    dispatchers = coreModule.dispatchers
                )
            },
            createFilterFeature = {
                FilterFeature(
                    ratingsFilterStorageValue = RatingsFilterStorageValue(
                        settings = coreModule.settings,
                        key = "ratings_filter_storage_key"
                    )
                )
            }
        )
    }
}
