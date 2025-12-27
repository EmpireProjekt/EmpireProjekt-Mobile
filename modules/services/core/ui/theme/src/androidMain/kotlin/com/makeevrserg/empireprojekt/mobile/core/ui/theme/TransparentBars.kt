package com.makeevrserg.empireprojekt.mobile.core.ui.theme

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import android.view.Window
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.window.DialogWindowProvider
import androidx.core.view.WindowInsetsControllerCompat

private tailrec fun Context.findWindow(): Window? =
    when (this) {
        is Activity -> window
        is ContextWrapper -> baseContext.findWindow()
        else -> null
    }

private fun findWindow(view: View): Window? {
    return (view.parent as? DialogWindowProvider)
        ?.window
        ?: view.context.findWindow()
}

@Composable
internal actual fun TransparentBars(isDarkTheme: Boolean) {
    val view = LocalView.current
    val window = findWindow(view)
    LaunchedEffect(view, window, isDarkTheme) {
        if (window == null) return@LaunchedEffect
        val controller = WindowInsetsControllerCompat(window, view)
        controller.isAppearanceLightStatusBars = !isDarkTheme
        controller.isAppearanceLightNavigationBars = !isDarkTheme
    }
}
