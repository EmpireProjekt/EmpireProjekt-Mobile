package com.makeevrserg.empireprojekt.mobile.features.rating.user.data

import com.makeevrserg.empireprojekt.mobile.api.empireapi.rating.api.RatingApi
import com.makeevrserg.empireprojekt.mobile.features.rating.model.RatingUserId
import kotlinx.coroutines.withContext
import ru.astrainteractive.empireapi.models.rating.RatingModel
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

internal class RatingUserRepositoryImpl(
    private val ratingApi: RatingApi,
    private val dispatchers: KotlinDispatchers
) : RatingUserRepository {
    override suspend fun getRatings(
        userId: RatingUserId,
        page: Int,
        size: Int
    ): List<RatingModel> = withContext(dispatchers.IO) {
        ratingApi.ratings(
            page = page,
            size = size,
            userId = userId.value
        ).data
    }
}
