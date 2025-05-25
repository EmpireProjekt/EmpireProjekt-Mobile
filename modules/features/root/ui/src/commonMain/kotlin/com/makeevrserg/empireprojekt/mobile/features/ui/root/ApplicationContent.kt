package com.makeevrserg.empireprojekt.mobile.features.ui.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.makeevrserg.empireprojekt.mobile.features.root.RootComponent
import com.makeevrserg.empireprojekt.mobile.features.root.screen.DefaultRootScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.root.screen.RootRouter
import com.makeevrserg.empireprojekt.mobile.features.ui.info.InfoScreen
import com.makeevrserg.empireprojekt.mobile.features.ui.map.AndroidMapView
import com.makeevrserg.empireprojekt.mobile.features.ui.pager.PagerScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.user.RatingUserScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.RatingUsersScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.splash.SplashScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.towny.towns.TownsScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.votes.VotesScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.votes.VotesScreenWebViewComponent
import com.makeevrserg.empireprojekt.mobile.services.core.LinkBrowser

@Composable
fun ApplicationContent(
    rootComponent: RootComponent,
    linkBrowser: LinkBrowser,
    onThemeToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val childStack by rootComponent.rootScreenComponent.childStack.subscribeAsState()
    Children(
        stack = childStack,
        modifier = modifier.fillMaxSize(),
        animation = stackAnimation(slide())
    ) { configuration ->
        when (val screen = configuration.instance) {
            is DefaultRootScreenComponent.Configuration.Splash -> SplashScreenComponent(
                rootRouter = rootComponent.rootScreenComponent,
                splashComponent = screen.splashComponent
            )

            is DefaultRootScreenComponent.Configuration.RatingUser -> RatingUserScreenComponent(
                ratingUserComponent = screen.ratingUserComponent,
                popComponent = rootComponent.rootScreenComponent
            )

            is DefaultRootScreenComponent.Configuration.Pager -> PagerScreenComponent(
                pagerComponent = screen.pagerComponent,
                menuScreen = { modifier, child ->
                    InfoScreen(
                        linkBrowser = linkBrowser,
                        onThemeToggle = onThemeToggle,
                        onTownsClick = {
                            rootComponent.rootScreenComponent.push(RootRouter.Configuration.Towns)
                        },
                        onRatingsClick = {
                            rootComponent.rootScreenComponent.push(RootRouter.Configuration.RatingUsers)
                        },
                        onVotesClick = {
                            rootComponent.rootScreenComponent.push(RootRouter.Configuration.Votes)
                        }
                    )
                },
                mapScreen = { modifier, child ->
                    AndroidMapView()
                }
            )

            is DefaultRootScreenComponent.Configuration.RatingUsers -> RatingUsersScreenComponent(
                popComponent = rootComponent.rootScreenComponent,
                ratingUsersComponent = screen.ratingUsersComponent
            )

            is DefaultRootScreenComponent.Configuration.Towns -> TownsScreenComponent(
                popComponent = rootComponent.rootScreenComponent,
                townsComponent = screen.townsComponent
            )

            DefaultRootScreenComponent.Configuration.Votes -> VotesScreenComponent(
                onPop = rootComponent.rootScreenComponent::pop,
                onClick = { url ->
                    rootComponent.rootScreenComponent.push(RootRouter.Configuration.VoteUrl(url))
                }
            )

            is DefaultRootScreenComponent.Configuration.VoteUrl -> VotesScreenWebViewComponent(
                url = screen.url,
                onPop = rootComponent.rootScreenComponent::pop
            )
        }
    }
}
