package com.makeevrserg.empireprojekt.mobile.di

import com.makeevrserg.empireprojekt.mobile.api.empireapi.di.EmpireApiModule
import com.makeevrserg.empireprojekt.mobile.features.rating.impl.user.di.DefaultRatingUserModule
import com.makeevrserg.empireprojekt.mobile.features.rating.impl.userlist.di.DefaultRatingUsersModule
import com.makeevrserg.empireprojekt.mobile.features.root.api.di.RootComponentModule
import com.makeevrserg.empireprojekt.mobile.features.root.impl.di.DefaultRootComponentModule
import com.makeevrserg.empireprojekt.mobile.features.root.pager.impl.di.DefaultPagerModule
import com.makeevrserg.empireprojekt.mobile.features.splash.impl.di.DefaultSplashComponentModule
import com.makeevrserg.empireprojekt.mobile.features.status.api.di.StatusModule
import com.makeevrserg.empireprojekt.mobile.features.status.impl.di.DefaultStatusModule
import com.makeevrserg.empireprojekt.mobile.features.theme.api.di.ThemeSwitcherModule
import com.makeevrserg.empireprojekt.mobile.features.theme.impl.di.DefaultThemeSwitcherModule
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.di.DefaultTownsModule
import com.makeevrserg.empireprojekt.mobile.features.webview.impl.di.DefaultWebViewModule
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule

internal class RootModule {

    val coreModule: CoreModule.Default by lazy {
        CoreModule.Default()
    }

    private val empireApiModule: EmpireApiModule by lazy {
        EmpireApiModule.Default(coreModule = coreModule)
    }

    val themeSwitcherModule: ThemeSwitcherModule by lazy {
        DefaultThemeSwitcherModule(coreModule = coreModule)
    }

    val statusModule: StatusModule by lazy {
        DefaultStatusModule(coreModule = coreModule)
    }

    val rootComponentModule: RootComponentModule by lazy {
        DefaultRootComponentModule(
            coreModule = coreModule,
            splashComponentModule = DefaultSplashComponentModule(coreModule = coreModule),
            ratingUserModule = DefaultRatingUserModule(
                ratingRepository = empireApiModule.ratingRepository,
                coreModule = coreModule
            ),
            ratingUsersModule = DefaultRatingUsersModule(
                ratingRepository = empireApiModule.ratingRepository,
                coreModule = coreModule
            ),
            townsModule = DefaultTownsModule(
                townRepository = empireApiModule.townRepository,
                coreModule = coreModule
            ),
            pagerModule = DefaultPagerModule(coreModule = coreModule),
            webViewModule = DefaultWebViewModule()
        )
    }
}
