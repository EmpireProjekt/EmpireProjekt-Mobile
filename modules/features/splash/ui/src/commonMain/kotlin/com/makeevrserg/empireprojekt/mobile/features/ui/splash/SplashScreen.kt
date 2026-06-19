package com.makeevrserg.empireprojekt.mobile.features.ui.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.features.root.screen.RootRouter
import com.makeevrserg.empireprojekt.mobile.features.splash.presentation.SplashComponent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreenComponent(
    splashComponent: SplashComponent,
    rootRouter: RootRouter,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(key1 = Unit) {
        splashComponent.screenChannelFlow.collectLatest { label ->
            when (label) {
                is SplashComponent.Label.InitialLaunch -> {
                    rootRouter.replaceCurrent(RootRouter.Configuration.Pager)
                }
            }
        }
    }
    SplashComposableScreen(modifier = modifier)
}
