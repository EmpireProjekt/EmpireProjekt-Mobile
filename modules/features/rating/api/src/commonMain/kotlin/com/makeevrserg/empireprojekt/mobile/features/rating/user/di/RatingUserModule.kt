package com.makeevrserg.empireprojekt.mobile.features.rating.user.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.rating.user.component.RatingUserComponent

interface RatingUserModule {
    fun createRatingUserComponent(
        componentContext: ComponentContext,
        userId: Long,
        userName: String
    ): RatingUserComponent
}
