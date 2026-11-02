package com.makeevrserg.empireprojekt.mobile.features.map.ui.composable.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.core.ui.common.appbar.composable.AstraTopBarScreenComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.common.webview.composable.AstraWebViewComposable

@Composable
fun AndroidMapViewComposable(modifier: Modifier = Modifier) {
    AstraTopBarScreenComposable(
        title = "КАРТА",
        modifier = modifier
    ) {
        AstraWebViewComposable(
            url = "https://map.astrainteractive.ru",
            modifier = Modifier.weight(1f)
        )
    }
}
