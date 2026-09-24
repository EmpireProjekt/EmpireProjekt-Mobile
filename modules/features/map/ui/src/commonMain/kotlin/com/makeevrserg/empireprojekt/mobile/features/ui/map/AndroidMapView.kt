package com.makeevrserg.empireprojekt.mobile.features.ui.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.core.ui.appbar.AstraCenterAlignedTopAppBar
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.webview.AstraWebView

@Composable
fun AndroidMapView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.astraColors.surface.primaryVariant)
    ) {
        AstraCenterAlignedTopAppBar(title = "КАРТА")
        AstraWebView(
            url = "https://map.astrainteractive.ru",
            modifier = Modifier.weight(1f)
        )
    }
}
