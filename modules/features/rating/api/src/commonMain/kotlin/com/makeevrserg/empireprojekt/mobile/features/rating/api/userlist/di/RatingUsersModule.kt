package com.makeevrserg.empireprojekt.mobile.features.rating.api.userlist.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.rating.api.userlist.component.RatingUsersComponent

interface RatingUsersModule {
    fun createRatingUsersComponent(
        componentContext: ComponentContext,
        onShowUserRatingsCallback: RatingUsersComponent.OnShowUserRatingsCallback
    ): RatingUsersComponent
}
