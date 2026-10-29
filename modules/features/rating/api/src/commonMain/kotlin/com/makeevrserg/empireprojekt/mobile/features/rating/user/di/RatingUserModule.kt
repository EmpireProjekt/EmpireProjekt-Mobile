package com.makeevrserg.empireprojekt.mobile.features.rating.user.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.rating.model.RatingUserId
import com.makeevrserg.empireprojekt.mobile.features.rating.user.component.RatingUserComponent

interface RatingUserModule {
    fun createRatingUserComponent(
        componentContext: ComponentContext,
        userId: RatingUserId,
        userName: String
    ): RatingUserComponent
}
