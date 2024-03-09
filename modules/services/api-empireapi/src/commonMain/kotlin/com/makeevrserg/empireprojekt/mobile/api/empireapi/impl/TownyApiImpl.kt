package com.makeevrserg.empireprojekt.mobile.api.empireapi.impl

import com.makeevrserg.empireprojekt.mobile.api.empireapi.TownyApi
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

class TownyApiImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String = "http://192.168.1.2:8080"
) : TownyApi {

    override suspend fun towns(page: Int, size: Int): GenericPagedModel<TownModel> {
        return httpClient.post {
            url("$baseUrl/towny/towns")
            parameter("page", page)
            parameter("size", size)
            contentType(ContentType.Application.Json)
            setBody(body)
        }.body()
    }
}
