package com.makeevrserg.empireprojekt.mobile.features.rating.api.api

import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.Rating
import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingUser
import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingsFilter
import com.makeevrserg.empireprojekt.mobile.services.core.model.GenericPagedModel

interface RatingRepository {
    suspend fun users(page: Int, size: Int, body: RatingsFilter): GenericPagedModel<RatingUser>

    suspend fun user(id: Long): RatingUser

    suspend fun ratings(page: Int, size: Int, userId: Long): GenericPagedModel<Rating>
}
