package com.makeevrserg.empireprojekt.mobile.features.status.impl.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.makeevrserg.empireprojekt.mobile.features.status.api.di.StatusModule
import com.makeevrserg.empireprojekt.mobile.features.status.api.root.component.RootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.api.url.component.UrlStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.impl.root.component.DefaultRootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.api.UrlStatusRepository
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.component.DefaultUrlStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.ktor.KtorMinecraftStatusRepository
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.ktor.KtorUrlStatusRepository
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.mvikotlin.createUrlStatusStore
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule

class DefaultStatusModule(
    private val coreModule: CoreModule
) : StatusModule {

    override val rootStatusComponent: RootStatusComponent by lazy {
        val storeFactory = DefaultStoreFactory()
        val minecraftStatusRepository = KtorMinecraftStatusRepository(
            httpClient = coreModule.httpClient,
            dispatchers = coreModule.dispatchers
        )
        DefaultRootStatusComponent(
            statusComponents = listOf(
                createUrlStatusComponent(
                    title = "empireprojekt.ru",
                    urlStatusRepository = createUrlStatusRepository("https://empireprojekt.ru"),
                    storeFactory = storeFactory
                ),
                createUrlStatusComponent(
                    title = "astrainteractive.ru",
                    urlStatusRepository = createUrlStatusRepository("https://astrainteractive.ru"),
                    storeFactory = storeFactory
                ),
                createUrlStatusComponent(
                    title = "Dev: AstraLearner",
                    urlStatusRepository = createUrlStatusRepository(
                        "http://astralearner.empireprojekt.ru:8081/dictionaries/7"
                    ),
                    storeFactory = storeFactory
                ),
                createUrlStatusComponent(
                    title = "Prod: AstraLearner",
                    urlStatusRepository = createUrlStatusRepository(
                        "http://astralearner.empireprojekt.ru:8081/dictionaries/7"
                    ),
                    storeFactory = storeFactory
                ),
                createUrlStatusComponent(
                    title = "Prod: EmpireApi",
                    urlStatusRepository = createUrlStatusRepository("https://empireapi.astrainteractive.ru/status"),
                    storeFactory = storeFactory
                ),
                createUrlStatusComponent(
                    title = "Empire SMP",
                    urlStatusRepository = minecraftStatusRepository,
                    storeFactory = storeFactory
                )
            )
        )
    }

    private fun createUrlStatusRepository(url: String): UrlStatusRepository {
        return KtorUrlStatusRepository(
            url = url,
            httpClient = coreModule.httpClient,
            dispatchers = coreModule.dispatchers
        )
    }

    private fun createUrlStatusComponent(
        title: String,
        urlStatusRepository: UrlStatusRepository,
        storeFactory: StoreFactory
    ): UrlStatusComponent {
        return DefaultUrlStatusComponent(
            title = title,
            urlStatusStore = createUrlStatusStore(
                storeFactory = storeFactory,
                urlStatusRepository = urlStatusRepository
            )
        )
    }
}
