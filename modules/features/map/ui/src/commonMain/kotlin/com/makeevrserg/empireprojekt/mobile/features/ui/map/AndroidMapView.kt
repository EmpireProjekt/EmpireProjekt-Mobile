package com.makeevrserg.empireprojekt.mobile.features.ui.map

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.core.ui.appbar.AstraTopBarScreen
import com.makeevrserg.empireprojekt.mobile.core.ui.webview.AstraWebView

@Composable
fun AndroidMapView(modifier: Modifier = Modifier) {
    AstraTopBarScreen(title = "КАРТА", modifier = modifier) {
        AstraWebView(
            url = "https://map.astrainteractive.ru",
            modifier = Modifier.weight(1f)
        )
    }
}
