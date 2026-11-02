package com.makeevrserg.empireprojekt.mobile.wear

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.arkivanov.decompose.defaultComponentContext
import com.makeevrserg.empireprojekt.mobile.features.theme.ui.theme.ApplicationTheme
import com.makeevrserg.empireprojekt.mobile.wear.App.Companion.asEmpireApp
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.root.composable.screen.WearRootScreenComposable

class MainActivity : ComponentActivity() {
    private val wearRootModule get() = application.asEmpireApp().wearRootModule

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val rootComponent = wearRootModule.wearRootComponentModule.createRootComponent(
            componentContext = defaultComponentContext()
        )
        setContent {
            ApplicationTheme(wearRootModule.themeSwitcherModule.themeSwitcherComponent) {
                WearRootScreenComposable(rootComponent = rootComponent)
            }
        }
        splashScreen.setKeepOnScreenCondition { false }
    }
}
