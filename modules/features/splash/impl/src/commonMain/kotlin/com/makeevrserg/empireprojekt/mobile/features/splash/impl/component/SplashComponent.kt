package com.makeevrserg.empireprojekt.mobile.features.splash.impl.component

import kotlinx.coroutines.flow.Flow

interface SplashComponent {
    val screenChannelFlow: Flow<Label>

    sealed interface Label {
        class InitialLaunch(val value: Boolean) : Label
    }
}
