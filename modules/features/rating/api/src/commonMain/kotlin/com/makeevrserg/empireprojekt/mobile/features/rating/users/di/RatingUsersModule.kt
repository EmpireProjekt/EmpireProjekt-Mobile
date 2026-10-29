package com.makeevrserg.empireprojekt.mobile.features.rating.users.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.rating.users.component.RatingUsersComponent

interface RatingUsersModule {
    fun createRatingUsersComponent(
        componentContext: ComponentContext,
        onShowUserRatingsClicked: (userId: Long, userName: String) -> Unit
    ): RatingUsersComponent
}
