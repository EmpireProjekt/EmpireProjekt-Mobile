package com.makeevrserg.empireprojekt.mobile.features.status.impl.url.ktor

import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.api.UrlStatusRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.withContext
import ru.astrainteractive.klibs.mikro.core.coroutines.propagateCancellationException
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

internal class KtorMinecraftStatusRepository(
    private val httpClient: HttpClient,
    private val dispatchers: KotlinDispatchers
) : UrlStatusRepository {

    override suspend fun isActive(): Result<Boolean> {
        return runCatching {
            val request = withContext(dispatchers.IO) {
                httpClient.get("https://api.minetools.eu/ping/play.empireprojekt.ru")
            }
            request.body<MinecraftStatusResponse>()
        }.propagateCancellationException()
            .map { true }
    }
}
