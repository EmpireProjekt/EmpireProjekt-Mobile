package com.makeevrserg.empireprojekt.mobile.api.empireapi.rating.ktor

import com.makeevrserg.empireprojekt.mobile.api.empireapi.rating.model.UserRatingsRequest
import com.makeevrserg.empireprojekt.mobile.features.rating.api.api.RatingRepository
import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.Rating
import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingUser
import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingsFilter
import com.makeevrserg.empireprojekt.mobile.services.core.model.PagedData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

internal class KtorRatingRepository(
    private val httpClient: HttpClient,
    private val baseUrl: String
) : RatingRepository {

    override suspend fun users(page: Int, size: Int, body: RatingsFilter): PagedData<RatingUser> {
        return httpClient.post {
            url("$baseUrl/rating/users")
            parameter("page", page)
            parameter("size", size)
            contentType(ContentType.Application.Json)
            setBody(body)
        }.body()
    }

    override suspend fun user(id: Long): RatingUser {
        return httpClient.get {
            url("$baseUrl/rating/user")
            contentType(ContentType.Application.Json)
            parameter("user_id", id)
        }.body()
    }

    override suspend fun ratings(page: Int, size: Int, userId: Long): PagedData<Rating> {
        return httpClient.post {
            url("$baseUrl/rating/user/votes")
            parameter("page", page)
            parameter("size", size)
            contentType(ContentType.Application.Json)
            setBody(UserRatingsRequest(id = userId))
        }.body()
    }
}
