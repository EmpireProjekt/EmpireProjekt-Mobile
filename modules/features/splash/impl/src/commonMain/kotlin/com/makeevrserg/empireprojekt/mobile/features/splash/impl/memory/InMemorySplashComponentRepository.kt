package com.makeevrserg.empireprojekt.mobile.features.splash.impl.memory

import com.makeevrserg.empireprojekt.mobile.features.splash.impl.api.SplashComponentRepository

internal class InMemorySplashComponentRepository : SplashComponentRepository {
    override fun isInitialLaunch(): Boolean {
        return true
    }
}
