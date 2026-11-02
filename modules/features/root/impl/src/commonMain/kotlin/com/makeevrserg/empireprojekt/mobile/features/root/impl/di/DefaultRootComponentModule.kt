package com.makeevrserg.empireprojekt.mobile.features.root.impl.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.rating.api.user.di.RatingUserModule
import com.makeevrserg.empireprojekt.mobile.features.rating.api.userlist.component.RatingUsersComponent
import com.makeevrserg.empireprojekt.mobile.features.rating.api.userlist.di.RatingUsersModule
import com.makeevrserg.empireprojekt.mobile.features.root.api.api.RootRouter
import com.makeevrserg.empireprojekt.mobile.features.root.api.component.RootComponent
import com.makeevrserg.empireprojekt.mobile.features.root.api.component.RootScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.root.api.di.RootComponentModule
import com.makeevrserg.empireprojekt.mobile.features.root.api.pager.di.PagerModule
import com.makeevrserg.empireprojekt.mobile.features.root.api.splash.di.SplashComponentModule
import com.makeevrserg.empireprojekt.mobile.features.root.impl.component.DefaultRootComponent
import com.makeevrserg.empireprojekt.mobile.features.root.impl.component.DefaultRootScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.di.TownsModule
import com.makeevrserg.empireprojekt.mobile.features.webview.api.component.WebViewComponent
import com.makeevrserg.empireprojekt.mobile.features.webview.api.di.WebViewModule
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule

class DefaultRootComponentModule(
    private val coreModule: CoreModule,
    private val splashComponentModule: SplashComponentModule,
    private val ratingUserModule: RatingUserModule,
    private val ratingUsersModule: RatingUsersModule,
    private val townsModule: TownsModule,
    private val pagerModule: PagerModule,
    private val webViewModule: WebViewModule
) : RootComponentModule {

    override fun createRootComponent(componentContext: ComponentContext): RootComponent {
        return DefaultRootComponent(
            componentContext = componentContext,
            rootComponentModule = this
        )
    }

    override fun createRootScreenComponent(componentContext: ComponentContext): RootScreenComponent {
        return DefaultRootScreenComponent(
            componentContext = componentContext,
            serializersModule = coreModule.jsonConfiguration.serializersModule,
            rootComponentModule = this
        )
    }

    override fun createRootScreenChild(
        configuration: RootRouter.Configuration,
        componentContext: ComponentContext,
        rootRouter: RootRouter
    ): RootScreenComponent.ScreenChild {
        return when (configuration) {
            RootRouter.Configuration.Splash -> RootScreenComponent.ScreenChild.Splash(
                splashComponent = splashComponentModule.createSplashComponent(
                    componentContext = componentContext
                )
            )

            is RootRouter.Configuration.RatingUser -> RootScreenComponent.ScreenChild.RatingUser(
                ratingUserComponent = ratingUserModule.createRatingUserComponent(
                    componentContext = componentContext,
                    userId = configuration.userId,
                    userName = configuration.userName
                )
            )

            RootRouter.Configuration.Pager -> RootScreenComponent.ScreenChild.Pager(
                pagerComponent = pagerModule.createPagerComponent(
                    componentContext = componentContext
                )
            )

            RootRouter.Configuration.RatingUsers -> RootScreenComponent.ScreenChild.RatingUsers(
                ratingUsersComponent = ratingUsersModule.createRatingUsersComponent(
                    componentContext = componentContext,
                    onShowUserRatingsCallback = RatingUsersComponent.OnShowUserRatingsCallback { userId, userName ->
                        rootRouter.push(RootRouter.Configuration.RatingUser(userId, userName))
                    }
                )
            )

            RootRouter.Configuration.Towns -> RootScreenComponent.ScreenChild.Towns(
                townsComponent = townsModule.createTownsComponent(
                    componentContext = componentContext
                )
            )

            RootRouter.Configuration.Votes -> RootScreenComponent.ScreenChild.Votes

            is RootRouter.Configuration.WebView -> RootScreenComponent.ScreenChild.WebView(
                webViewComponent = webViewModule.createWebViewComponent(
                    componentContext = componentContext,
                    url = configuration.url,
                    onPopCallback = WebViewComponent.OnPopCallback(rootRouter::pop)
                )
            )
        }
    }
}
