package com.makeevrserg.empireprojekt.mobile

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.arkivanov.decompose.defaultComponentContext
import com.makeevrserg.empireprojekt.mobile.App.Companion.asEmpireApp
import com.makeevrserg.empireprojekt.mobile.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.root.ui.composable.screen.ApplicationContentComposable
import com.makeevrserg.empireprojekt.mobile.features.theme.ui.theme.ApplicationTheme

class MainActivity : ComponentActivity() {
    private val requestPermissionLauncher = registerForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        callback = { isGranted: Boolean ->
            if (isGranted) launchStatusService()
        }
    )
    private val rootModule: RootModule
        get() = application.asEmpireApp().rootModule

    private fun hasNotificationPermission(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return true
        val notificationPermission = ActivityCompat.checkSelfPermission(
            applicationContext,
            Manifest.permission.POST_NOTIFICATIONS
        )
        return notificationPermission == PackageManager.PERMISSION_GRANTED
    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return
        requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
    }

    private fun launchStatusService() {
        if (!hasNotificationPermission()) {
            askNotificationPermission()
            return
        }
        val service = Intent(applicationContext, StatusService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            applicationContext.startForegroundService(service)
        } else {
            applicationContext.startService(service)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        launchStatusService()
        val componentContext = defaultComponentContext()
        val rootComponent = rootModule.rootComponentModule.createRootComponent(componentContext)
        setContent {
            ApplicationTheme(rootModule.themeSwitcherModule.themeSwitcherComponent) {
                ApplicationContentComposable(
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
        requestPermissionLauncher.unregister()
        super.onStop()
    }
}
