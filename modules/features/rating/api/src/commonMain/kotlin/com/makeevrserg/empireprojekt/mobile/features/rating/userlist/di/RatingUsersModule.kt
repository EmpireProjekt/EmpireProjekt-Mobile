package com.makeevrserg.empireprojekt.mobile.features.rating.userlist.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.rating.model.RatingUserId
import com.makeevrserg.empireprojekt.mobile.features.rating.userlist.component.RatingUsersComponent

interface RatingUsersModule {
    fun createRatingUsersComponent(
        componentContext: ComponentContext,
        onShowUserRatingsClicked: (userId: RatingUserId, userName: String) -> Unit
    ): RatingUsersComponent
}
