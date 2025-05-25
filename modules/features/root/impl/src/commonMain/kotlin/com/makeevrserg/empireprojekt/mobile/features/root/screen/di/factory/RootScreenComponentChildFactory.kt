package com.makeevrserg.empireprojekt.mobile.features.root.screen.di.factory

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.rating.users.di.RatingUsersModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.root.screen.DefaultRootScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.root.screen.RootRouter
import com.makeevrserg.empireprojekt.mobile.features.root.screen.RootScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.di.TownsModule
import ru.astrainteractive.klibs.kdi.Factory

class RootScreenComponentChildFactory(
    private val config: RootRouter.Configuration,
    private val childContext: ComponentContext,
    private val rootModule: RootModule,
    private val instance: RootScreenComponent,
    private val ratingUsersModule: RatingUsersModule,
    private val townsModule: TownsModule,
    private val onRootNavigation: (RootRouter.Configuration) -> Unit
) : Factory<DefaultRootScreenComponent.Configuration> {
    override fun create(): DefaultRootScreenComponent.Configuration {
        return when (config) {
            RootRouter.Configuration.Splash -> {
                DefaultRootScreenComponent.Configuration.Splash(
                    splashComponent = rootModule.splashModule.createSplashComponent(
                        componentContext = childContext
                    )
                )
            }

            is RootRouter.Configuration.RatingUser -> {
                DefaultRootScreenComponent.Configuration.RatingUser(
                    ratingUserComponent = rootModule.ratingUserModule.createRatingUserComponent(
                        componentContext = childContext,
                        userId = config.userId,
                        userName = config.userName
                    )
                )
            }

            RootRouter.Configuration.Pager -> {
                DefaultRootScreenComponent.Configuration.Pager(
                    pagerComponent = rootModule.pagerModule.createPagerComponent(
                        componentContext = childContext,
                        onRootNavigation = { configuration ->
                            instance.push(configuration)
                        }
                    )
                )
            }

            RootRouter.Configuration.RatingUsers -> DefaultRootScreenComponent.Configuration.RatingUsers(
                ratingUsersComponent = ratingUsersModule.createRatingUsersComponent(
                    componentContext = childContext,
                    onShowUserRatingsClicked = { id, userName ->
                        val configuration = RootRouter.Configuration.RatingUser(id, userName)
                        onRootNavigation.invoke(configuration)
                    }
                )
            )

            RootRouter.Configuration.Towns -> DefaultRootScreenComponent.Configuration.Towns(
                townsComponent = townsModule.createTownsComponent(
                    componentContext = childContext
                )
            )

            RootRouter.Configuration.Votes -> DefaultRootScreenComponent.Configuration.Votes
            is RootRouter.Configuration.VoteUrl -> DefaultRootScreenComponent.Configuration.VoteUrl(
                url = config.url
            )
        }
    }
}
