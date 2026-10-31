package com.makeevrserg.empireprojekt.mobile.features.ui.root.composable.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.makeevrserg.empireprojekt.mobile.core.ui.decompose.model.DecomposeScreen
import com.makeevrserg.empireprojekt.mobile.features.root.api.api.RootRouter
import com.makeevrserg.empireprojekt.mobile.features.root.api.component.RootComponent
import com.makeevrserg.empireprojekt.mobile.features.root.api.component.RootScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.root.api.component.RootScreenComponent.ScreenChild.Pager
import com.makeevrserg.empireprojekt.mobile.features.root.api.component.RootScreenComponent.ScreenChild.RatingUser
import com.makeevrserg.empireprojekt.mobile.features.root.api.component.RootScreenComponent.ScreenChild.RatingUsers
import com.makeevrserg.empireprojekt.mobile.features.root.api.component.RootScreenComponent.ScreenChild.Splash
import com.makeevrserg.empireprojekt.mobile.features.root.api.component.RootScreenComponent.ScreenChild.Towns
import com.makeevrserg.empireprojekt.mobile.features.root.api.component.RootScreenComponent.ScreenChild.Votes
import com.makeevrserg.empireprojekt.mobile.features.root.api.component.RootScreenComponent.ScreenChild.WebView
import com.makeevrserg.empireprojekt.mobile.features.root.api.pager.component.PagerComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.info.composable.screen.InfoScreenComposable
import com.makeevrserg.empireprojekt.mobile.features.ui.map.composable.screen.AndroidMapViewComposable
import com.makeevrserg.empireprojekt.mobile.features.ui.pager.composable.screen.PagerScreenHostComposable
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.user.composable.screen.RatingUserScreenHostComposable
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.userlist.composable.screen.RatingUsersScreenHostComposable
import com.makeevrserg.empireprojekt.mobile.features.ui.splash.composable.screen.SplashScreenHostComposable
import com.makeevrserg.empireprojekt.mobile.features.ui.towny.towns.composable.screen.TownsScreenHostComposable
import com.makeevrserg.empireprojekt.mobile.features.ui.votes.composable.screen.VotesScreenComposable
import com.makeevrserg.empireprojekt.mobile.features.webview.map.composable.AndroidWebViewComposable
import com.makeevrserg.empireprojekt.mobile.services.core.link.api.LinkBrowser

@Composable
private fun RootPagerComposable(
    pagerComponent: PagerComponent,
    menuScreen: DecomposeScreen<PagerComponent.Child.Menu>
) {
    PagerScreenHostComposable(
        pagerComponent = pagerComponent,
        menuScreen = menuScreen,
        mapScreen = { _, _ -> AndroidMapViewComposable() }
    )
}

@Composable
private fun RootSplashComposable(
    child: Splash,
    rootScreenComponent: RootScreenComponent
) {
    SplashScreenHostComposable(
        rootRouter = rootScreenComponent,
        splashComponent = child.splashComponent
    )
}

@Composable
private fun RootRatingUserComposable(
    child: RatingUser,
    rootScreenComponent: RootScreenComponent
) {
    RatingUserScreenHostComposable(
        ratingUserComponent = child.ratingUserComponent,
        popComponent = rootScreenComponent
    )
}

@Composable
private fun RootRatingUsersComposable(
    child: RatingUsers,
    rootScreenComponent: RootScreenComponent
) {
    RatingUsersScreenHostComposable(
        popComponent = rootScreenComponent,
        ratingUsersComponent = child.ratingUsersComponent
    )
}

@Composable
private fun RootTownsComposable(
    child: Towns,
    rootScreenComponent: RootScreenComponent
) {
    TownsScreenHostComposable(
        popComponent = rootScreenComponent,
        townsComponent = child.townsComponent
    )
}

@Composable
private fun RootVotesComposable(rootScreenComponent: RootScreenComponent) {
    VotesScreenComposable(
        onPop = rootScreenComponent::pop,
        onClick = { url ->
            rootScreenComponent.push(RootRouter.Configuration.WebView(url))
        }
    )
}

@Composable
private fun RootWebViewComposable(child: WebView) {
    AndroidWebViewComposable(webViewComponent = child.webViewComponent)
}

@Composable
private fun RootChildComposable(
    child: RootScreenComponent.ScreenChild,
    rootScreenComponent: RootScreenComponent,
    menuScreen: DecomposeScreen<PagerComponent.Child.Menu>
) {
    when (child) {
        is Splash -> RootSplashComposable(child, rootScreenComponent)
        is RatingUser -> RootRatingUserComposable(child, rootScreenComponent)
        is Pager -> RootPagerComposable(child.pagerComponent, menuScreen)
        is RatingUsers -> RootRatingUsersComposable(child, rootScreenComponent)
        is Towns -> RootTownsComposable(child, rootScreenComponent)
        Votes -> RootVotesComposable(rootScreenComponent)
        is WebView -> RootWebViewComposable(child)
    }
}

@Suppress("LongMethod")
@Composable
fun ApplicationContentComposable(
    rootComponent: RootComponent,
    linkBrowser: LinkBrowser,
    onThemeToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val childStack by rootComponent.rootScreenComponent.childStack.subscribeAsState()
    val menuScreen: DecomposeScreen<PagerComponent.Child.Menu> = { _, _ ->
        InfoScreenComposable(
            onLinkClick = linkBrowser::openInBrowser,
            onThemeToggle = onThemeToggle,
            onTownsClick = {
                rootComponent.rootScreenComponent.push(RootRouter.Configuration.Towns)
            },
            onRatingsClick = {
                rootComponent.rootScreenComponent.push(RootRouter.Configuration.RatingUsers)
            },
            onVotesClick = {
                rootComponent.rootScreenComponent.push(RootRouter.Configuration.Votes)
            },
            onWikiClick = {
                rootComponent.rootScreenComponent.push(
                    RootRouter.Configuration.WebView("https://astra-interactive.github.io/EmpireNetwork/")
                )
            }
        )
    }
    Children(
        stack = childStack,
        modifier = modifier.fillMaxSize(),
        animation = stackAnimation(slide())
    ) { configuration ->
        RootChildComposable(
            child = configuration.instance,
            rootScreenComponent = rootComponent.rootScreenComponent,
            menuScreen = menuScreen
        )
    }
}
