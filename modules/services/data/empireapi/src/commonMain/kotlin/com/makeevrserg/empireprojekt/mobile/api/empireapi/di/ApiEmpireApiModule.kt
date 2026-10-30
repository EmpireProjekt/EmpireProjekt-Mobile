package com.makeevrserg.empireprojekt.mobile.api.empireapi.di

import com.makeevrserg.empireprojekt.mobile.api.empireapi.rating.api.RatingApi
import com.makeevrserg.empireprojekt.mobile.api.empireapi.rating.ktor.KtorRatingApi
import com.makeevrserg.empireprojekt.mobile.api.empireapi.towny.api.TownyApi
import com.makeevrserg.empireprojekt.mobile.api.empireapi.towny.ktor.KtorTownyApi
import com.makeevrserg.empireprojekt.mobile.buildkonfig.BuildKonfig
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule

interface ApiEmpireApiModule {
    val ratingApi: RatingApi
    val townyApi: TownyApi

    class Default(coreModule: CoreModule) : ApiEmpireApiModule {
        private val url = BuildKonfig.PROD_URL

        override val ratingApi: RatingApi by lazy {
            KtorRatingApi(httpClient = coreModule.httpClient, baseUrl = url)
        }

        override val townyApi: TownyApi by lazy {
            KtorTownyApi(httpClient = coreModule.httpClient, baseUrl = url)
        }
    }
}
