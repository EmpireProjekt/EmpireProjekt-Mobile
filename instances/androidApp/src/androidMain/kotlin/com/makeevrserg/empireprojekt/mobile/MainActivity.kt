package com.makeevrserg.empireprojekt.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.defaultComponentContext
import com.makeevrserg.empireprojekt.mobile.application.App.Companion.asEmpireApp
import com.makeevrserg.empireprojekt.mobile.core.resources.R
import com.makeevrserg.empireprojekt.mobile.features.root.DefaultRootComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.ApplicationTheme
import com.makeevrserg.empireprojekt.mobile.features.ui.root.ApplicationContent
import com.makeevrserg.empireprojekt.mobile.service.controller.StatusServiceController
import com.makeevrserg.empireprojekt.mobile.service.controller.StatusServiceControllerImpl
import ru.astrainteractive.klibs.kdi.Provider
import ru.astrainteractive.klibs.kdi.getValue

class MainActivity : ComponentActivity() {
    private val statusServiceController: StatusServiceController by lazy {
        StatusServiceControllerImpl(this)
    }
    private val rootModule by Provider {
        application.asEmpireApp().rootModule
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setTheme(R.style.AppTheme)
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
    }

    override fun onStop() {
        statusServiceController.close()
        super.onStop()
    }
}
