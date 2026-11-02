package com.makeevrserg.empireprojekt.mobile.features.status.impl.url.ktor

import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.api.UrlStatusRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.withContext
import ru.astrainteractive.klibs.mikro.core.coroutines.propagateCancellationException
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

internal class KtorUrlStatusRepository(
    private val url: String,
    private val httpClient: HttpClient,
    private val dispatchers: KotlinDispatchers
) : UrlStatusRepository {
    override suspend fun isActive(): Result<Boolean> {
        return runCatching {
            val request = withContext(dispatchers.IO) {
                httpClient.get(url)
            }
            request.status == HttpStatusCode.OK
        }.propagateCancellationException()
    }
}
