package com.makeevrserg.empireprojekt.mobile.features.rating.impl.user.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.rating.api.api.RatingRepository
import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingUserId
import com.makeevrserg.empireprojekt.mobile.features.rating.api.user.component.RatingUserComponent
import com.makeevrserg.empireprojekt.mobile.features.rating.api.user.di.RatingUserModule
import com.makeevrserg.empireprojekt.mobile.features.rating.impl.user.component.DefaultRatingUserComponent
import com.makeevrserg.empireprojekt.mobile.features.rating.impl.user.component.RatingUserFeature
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule

class DefaultRatingUserModule(
    private val ratingRepository: RatingRepository,
    private val coreModule: CoreModule
) : RatingUserModule {
    override fun createRatingUserComponent(
        componentContext: ComponentContext,
        userId: RatingUserId,
        userName: String
    ): RatingUserComponent {
        return DefaultRatingUserComponent(
            componentContext = componentContext,
            createRatingUserFeature = {
                RatingUserFeature(
                    userId = userId,
                    userName = userName,
                    ratingRepository = ratingRepository,
                    dispatchers = coreModule.dispatchers
                )
            }
        )
    }
}
