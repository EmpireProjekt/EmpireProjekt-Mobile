package com.makeevrserg.empireprojekt.mobile.api.empireapi.towny.ktor

import com.makeevrserg.empireprojekt.mobile.api.empireapi.towny.api.TownyApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import ru.astrainteractive.empireapi.models.response.GenericPagedModel
import ru.astrainteractive.empireapi.models.towny.TownModel
import ru.astrainteractive.empireapi.models.towny.TownsFilterModel

internal class KtorTownyApi(
    private val httpClient: HttpClient,
    private val baseUrl: String
) : TownyApi {

    override suspend fun towns(page: Int, size: Int, filter: TownsFilterModel): GenericPagedModel<TownModel> {
        return httpClient.post {
            url("$baseUrl/towny/towns")
            parameter("page", page)
            parameter("size", size)
            contentType(ContentType.Application.Json)
            setBody(filter)
        }.body()
    }
}
