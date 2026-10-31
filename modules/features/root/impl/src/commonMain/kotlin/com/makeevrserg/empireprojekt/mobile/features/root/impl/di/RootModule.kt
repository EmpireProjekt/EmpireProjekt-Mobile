package com.makeevrserg.empireprojekt.mobile.features.root.impl.di

import com.makeevrserg.empireprojekt.mobile.api.empireapi.di.EmpireApiModule
import com.makeevrserg.empireprojekt.mobile.features.rating.api.user.di.RatingUserModule
import com.makeevrserg.empireprojekt.mobile.features.rating.api.userlist.di.RatingUsersModule
import com.makeevrserg.empireprojekt.mobile.features.rating.impl.user.di.RatingUserModuleImpl
import com.makeevrserg.empireprojekt.mobile.features.rating.impl.userlist.di.RatingUsersModuleImpl
import com.makeevrserg.empireprojekt.mobile.features.root.pager.impl.di.PagerModule
import com.makeevrserg.empireprojekt.mobile.features.splash.impl.di.SplashComponentModule
import com.makeevrserg.empireprojekt.mobile.features.status.api.di.StatusModule
import com.makeevrserg.empireprojekt.mobile.features.status.impl.di.StatusModuleImpl
import com.makeevrserg.empireprojekt.mobile.features.theme.api.di.ThemeSwitcherModule
import com.makeevrserg.empireprojekt.mobile.features.theme.impl.di.ThemeSwitcherModuleImpl
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.di.TownsModule
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.di.TownsModuleImpl
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule

interface RootModule {

    val coreModule: CoreModule
    val statusModule: StatusModule
    val themeSwitcherModule: ThemeSwitcherModule
    val splashModule: SplashComponentModule
    val ratingUserModule: RatingUserModule
    val ratingUsersModule: RatingUsersModule
    val townsModule: TownsModule
    val pagerModule: PagerModule
    val empireApiModule: EmpireApiModule

    class Default : RootModule {

        override val coreModule: CoreModule.Default by lazy {
            CoreModule.Default()
        }

        override val empireApiModule: EmpireApiModule by lazy {
            EmpireApiModule.Default(
                coreModule = coreModule
            )
        }
        override val themeSwitcherModule: ThemeSwitcherModule by lazy {
            ThemeSwitcherModuleImpl(coreModule = coreModule)
        }

        override val statusModule: StatusModule by lazy {
            StatusModuleImpl(
                coreModule = coreModule
            )
        }

        override val splashModule: SplashComponentModule by lazy {
            SplashComponentModule.Default(
                coreModule = coreModule
            )
        }

        override val townsModule: TownsModule by lazy {
            TownsModuleImpl(
                empireApiModule = empireApiModule,
                coreModule = coreModule
            )
        }

        override val ratingUserModule: RatingUserModule
            get() = RatingUserModuleImpl(
                empireApiModule = empireApiModule,
                coreModule = coreModule
            )

        override val ratingUsersModule: RatingUsersModule by lazy {
            RatingUsersModuleImpl(
                empireApiModule = empireApiModule,
                coreModule = coreModule

            )
        }

        override val pagerModule: PagerModule by lazy {
            PagerModule.Default(
                coreModule = coreModule
            )
        }
    }
}
