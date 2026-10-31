package com.makeevrserg.empireprojekt.mobile.features.ui.map

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.core.ui.appbar.composable.AstraTopBarScreenComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.webview.composable.AstraWebViewComposable

@Composable
fun AndroidMapView(modifier: Modifier = Modifier) {
    AstraTopBarScreenComposable(title = "КАРТА", modifier = modifier) {
        AstraWebViewComposable(
            url = "https://map.astrainteractive.ru",
            modifier = Modifier.weight(1f)
        )
    }
}
