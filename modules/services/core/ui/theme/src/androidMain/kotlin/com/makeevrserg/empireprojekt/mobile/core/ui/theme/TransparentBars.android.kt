package com.makeevrserg.empireprojekt.mobile.core.ui.theme

import android.app.Activity
import android.content.ContextWrapper
import android.view.View
import android.view.Window
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.window.DialogWindowProvider
import androidx.core.view.WindowInsetsControllerCompat

private fun findWindow(view: View): Window? {
    val dialogWindow = (view.parent as? DialogWindowProvider)?.window
    if (dialogWindow != null) {
        return dialogWindow
    }
    var context = view.context
    while (context is ContextWrapper) {
        if (context is Activity) {
            return context.window
        }
        context = context.baseContext
    }
    return null
}

@Composable
internal actual fun TransparentBars(isDarkTheme: Boolean) {
    val view = LocalView.current
    val window = findWindow(view)
    LaunchedEffect(
        view,
        window,
        isDarkTheme
    ) {
        if (window == null) return@LaunchedEffect
        val controller = WindowInsetsControllerCompat(window, view)
        controller.isAppearanceLightStatusBars = !isDarkTheme
        controller.isAppearanceLightNavigationBars = !isDarkTheme
    }
}
