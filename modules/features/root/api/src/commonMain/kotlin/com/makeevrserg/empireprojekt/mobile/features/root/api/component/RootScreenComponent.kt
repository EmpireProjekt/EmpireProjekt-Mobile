package com.makeevrserg.empireprojekt.mobile.features.root.api.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import com.makeevrserg.empireprojekt.mobile.features.rating.api.user.component.RatingUserComponent
import com.makeevrserg.empireprojekt.mobile.features.rating.api.userlist.component.RatingUsersComponent
import com.makeevrserg.empireprojekt.mobile.features.root.api.api.RootRouter
import com.makeevrserg.empireprojekt.mobile.features.root.api.pager.component.PagerComponent
import com.makeevrserg.empireprojekt.mobile.features.root.api.splash.component.SplashComponent
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.component.TownsComponent
import com.makeevrserg.empireprojekt.mobile.features.webview.api.component.WebViewComponent

interface RootScreenComponent : BackHandlerOwner, RootRouter {
    val childStack: Value<ChildStack<*, ScreenChild>>

    sealed interface ScreenChild {
        class Splash(
            val splashComponent: SplashComponent
        ) : ScreenChild

        class RatingUser(
            val ratingUserComponent: RatingUserComponent
        ) : ScreenChild

        class RatingUsers(
            val ratingUsersComponent: RatingUsersComponent
        ) : ScreenChild

        class Towns(
            val townsComponent: TownsComponent
        ) : ScreenChild

        class Pager(
            val pagerComponent: PagerComponent
        ) : ScreenChild

        data object Votes : ScreenChild

        class WebView(
            val webViewComponent: WebViewComponent
        ) : ScreenChild
    }
}
