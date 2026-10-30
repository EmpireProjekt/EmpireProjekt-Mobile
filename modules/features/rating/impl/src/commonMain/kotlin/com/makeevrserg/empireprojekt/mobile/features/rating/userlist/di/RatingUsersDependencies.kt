package com.makeevrserg.empireprojekt.mobile.features.rating.userlist.di

import com.makeevrserg.empireprojekt.mobile.features.rating.userlist.data.RatingUsersRepository

internal interface RatingUsersDependencies {
    val ratingUsersRepository: RatingUsersRepository
    class Default(override val ratingUsersRepository: RatingUsersRepository) : RatingUsersDependencies
}
