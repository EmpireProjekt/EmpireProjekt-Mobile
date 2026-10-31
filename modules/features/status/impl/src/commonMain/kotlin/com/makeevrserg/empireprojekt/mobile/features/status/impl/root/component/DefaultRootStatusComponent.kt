package com.makeevrserg.empireprojekt.mobile.features.status.impl.root.component

import com.makeevrserg.empireprojekt.mobile.features.status.api.root.component.RootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.api.url.component.UrlStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.impl.di.RootStatusComponentDependencies
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.component.DefaultUrlStatusComponent

internal class DefaultRootStatusComponent(
    private val dependencies: RootStatusComponentDependencies
) : RootStatusComponent,
    RootStatusComponentDependencies by dependencies {
    override val statusComponents: List<UrlStatusComponent> = buildList {
        DefaultUrlStatusComponent(
            title = "empireprojekt.ru",
            urlStatusRepository = createUrlStatusRepository(
                "https://empireprojekt.ru"
            ),
            storeFactory = storeFactory,
        ).run(::add)

        DefaultUrlStatusComponent(
            title = "astrainteractive.ru",
            urlStatusRepository = createUrlStatusRepository(
                "https://astrainteractive.ru"
            ),
            storeFactory = storeFactory,
        ).run(::add)

        DefaultUrlStatusComponent(
            title = "Dev: AstraLearner",
            urlStatusRepository = createUrlStatusRepository(
                "http://astralearner.empireprojekt.ru:8081/dictionaries/7"
            ),
            storeFactory = storeFactory,
        ).run(::add)

        DefaultUrlStatusComponent(
            title = "Prod: AstraLearner",
            urlStatusRepository = createUrlStatusRepository(
                "http://astralearner.empireprojekt.ru:8081/dictionaries/7"
            ),
            storeFactory = storeFactory,
        ).run(::add)

        DefaultUrlStatusComponent(
            title = "Prod: EmpireApi",
            urlStatusRepository = createUrlStatusRepository(
                "https://empireapi.astrainteractive.ru/status"
            ),
            storeFactory = storeFactory,
        ).run(::add)

        DefaultUrlStatusComponent(
            title = "Empire SMP",
            urlStatusRepository = minecraftStatusRepository,
            storeFactory = storeFactory,
        ).run(::add)
    }
}
