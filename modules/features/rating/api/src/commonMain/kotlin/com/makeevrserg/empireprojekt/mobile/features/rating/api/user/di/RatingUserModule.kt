package com.makeevrserg.empireprojekt.mobile.features.rating.api.user.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingUserId
import com.makeevrserg.empireprojekt.mobile.features.rating.api.user.component.RatingUserComponent

interface RatingUserModule {
    fun createRatingUserComponent(
        componentContext: ComponentContext,
        userId: RatingUserId,
        userName: String
    ): RatingUserComponent
}
