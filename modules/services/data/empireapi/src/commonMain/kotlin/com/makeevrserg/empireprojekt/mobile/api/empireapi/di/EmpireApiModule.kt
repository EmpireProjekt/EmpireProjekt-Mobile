package com.makeevrserg.empireprojekt.mobile.api.empireapi.di

import com.makeevrserg.empireprojekt.mobile.api.empireapi.rating.ktor.KtorRatingRepository
import com.makeevrserg.empireprojekt.mobile.api.empireapi.towny.ktor.KtorTownRepository
import com.makeevrserg.empireprojekt.mobile.buildkonfig.BuildKonfig
import com.makeevrserg.empireprojekt.mobile.features.rating.api.api.RatingRepository
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.api.TownRepository
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule

interface EmpireApiModule {
    val ratingRepository: RatingRepository
    val townRepository: TownRepository

    class Default(coreModule: CoreModule) : EmpireApiModule {
        private val url = BuildKonfig.PROD_URL

        override val ratingRepository: RatingRepository by lazy {
            KtorRatingRepository(httpClient = coreModule.httpClient, baseUrl = url)
        }

        override val townRepository: TownRepository by lazy {
            KtorTownRepository(httpClient = coreModule.httpClient, baseUrl = url)
        }
    }
}
