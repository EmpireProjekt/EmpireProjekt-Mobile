package com.makeevrserg.empireprojekt.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.arkivanov.decompose.defaultComponentContext
import com.makeevrserg.empireprojekt.mobile.application.App.Companion.asEmpireApp
import com.makeevrserg.empireprojekt.mobile.features.root.DefaultRootComponent
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.theme.ApplicationTheme
import com.makeevrserg.empireprojekt.mobile.features.ui.root.ApplicationContent
import com.makeevrserg.empireprojekt.mobile.service.controller.StatusServiceController
import com.makeevrserg.empireprojekt.mobile.service.controller.StatusServiceControllerImpl

class MainActivity : ComponentActivity() {
    private val statusServiceController: StatusServiceController by lazy {
        StatusServiceControllerImpl(this)
    }
    private val rootModule: RootModule.Default
        get() = application.asEmpireApp().rootModule

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        statusServiceController.launchStatusService()
        val componentContext = defaultComponentContext()
        val rootComponent = DefaultRootComponent(componentContext, rootModule)
        setContent {
            ApplicationTheme(rootModule.themeSwitcherModule.themeSwitcherComponent) {
                ApplicationContent(
                    rootComponent = rootComponent,
                    modifier = Modifier,
                    linkBrowser = rootModule.coreModule.linkBrowser,
                    onThemeToggle = { rootModule.themeSwitcherModule.themeSwitcherComponent.next() }
                )
            }
        }
        splashScreen.setKeepOnScreenCondition { false }
    }

    override fun onStop() {
        statusServiceController.close()
        super.onStop()
    }
}
