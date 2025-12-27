package com.makeevrserg.empireprojekt.mobile.wear

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.arkivanov.decompose.defaultComponentContext
import com.makeevrserg.empireprojekt.mobile.features.theme.ApplicationTheme
import com.makeevrserg.empireprojekt.mobile.wear.application.App.Companion.asEmpireApp
import com.makeevrserg.empireprojekt.mobile.wear.features.root.presentation.DefaultRootComponent
import com.makeevrserg.empireprojekt.mobile.wear.features.root.ui.RootScreen

class MainActivity : ComponentActivity() {
    private val wearRootModule get() = application.asEmpireApp().wearRootModule

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val rootComponent = DefaultRootComponent(
            componentContext = defaultComponentContext(),
            wearRootModule = wearRootModule
        )
        setContent {
            ApplicationTheme(wearRootModule.themeSwitcherModule.themeSwitcherComponent) {
                RootScreen(rootComponent = rootComponent)
            }
        }
        splashScreen.setKeepOnScreenCondition { false }
    }
}
