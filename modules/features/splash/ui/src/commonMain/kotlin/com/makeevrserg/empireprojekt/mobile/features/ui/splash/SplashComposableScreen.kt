package com.makeevrserg.empireprojekt.mobile.features.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.resources.img_splash
import com.makeevrserg.empireprojekt.mobile.core.ui.common.navBarsPadding
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asPainter

@Composable
internal fun SplashComposableScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primaryVariant)
    ) {
        Spacer(Modifier.statusBarsPadding())
        Box(Modifier.fillMaxSize()) {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = MR.images.img_splash.asPainter(),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
            }
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .navBarsPadding(),
                contentAlignment = Alignment.TopCenter
            ) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = AppTheme.astraColors.astraLogo.astraOrange,
                    backgroundColor = AppTheme.astraColors.astraLogo.astraYellow
                )
            }
        }
    }
}

@Preview
@Composable
private fun SplashComposableScreenPreview() {
    AdaptThemeFade {
        SplashComposableScreen()
    }
}
