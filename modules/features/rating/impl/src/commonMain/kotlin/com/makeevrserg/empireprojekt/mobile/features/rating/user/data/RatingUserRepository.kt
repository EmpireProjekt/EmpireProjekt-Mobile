package com.makeevrserg.empireprojekt.mobile.features.rating.user.data

import com.makeevrserg.empireprojekt.mobile.features.rating.model.RatingUserId
import ru.astrainteractive.empireapi.models.rating.RatingModel

internal interface RatingUserRepository {
    suspend fun getRatings(userId: RatingUserId, page: Int, size: Int): List<RatingModel>
}
