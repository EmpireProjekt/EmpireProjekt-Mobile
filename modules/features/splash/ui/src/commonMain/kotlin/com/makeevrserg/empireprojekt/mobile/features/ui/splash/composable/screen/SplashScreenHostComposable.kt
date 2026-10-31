package com.makeevrserg.empireprojekt.mobile.features.ui.splash.composable.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.features.root.api.api.RootRouter
import com.makeevrserg.empireprojekt.mobile.features.root.api.splash.component.SplashComponent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreenHostComposable(
    splashComponent: SplashComponent,
    rootRouter: RootRouter,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(key1 = Unit) {
        splashComponent.screenChannelFlow.collectLatest { label ->
            when (label) {
                is SplashComponent.Label.InitialLaunch -> rootRouter.replaceCurrent(RootRouter.Configuration.Pager)
            }
        }
    }
    SplashScreenComposable(modifier = modifier)
}
