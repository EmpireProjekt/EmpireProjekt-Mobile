package com.makeevrserg.empireprojekt.mobile.api.empireapi.towny.ktor

import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.api.TownRepository
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.Town
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.TownsFilter
import com.makeevrserg.empireprojekt.mobile.services.core.model.GenericPagedModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

internal class KtorTownRepository(
    private val httpClient: HttpClient,
    private val baseUrl: String
) : TownRepository {

    override suspend fun towns(page: Int, size: Int, filter: TownsFilter): GenericPagedModel<Town> {
        return httpClient.post {
            url("$baseUrl/towny/towns")
            parameter("page", page)
            parameter("size", size)
            contentType(ContentType.Application.Json)
            setBody(filter)
        }.body()
    }
}
