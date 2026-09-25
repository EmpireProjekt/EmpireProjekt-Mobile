package com.makeevrserg.empireprojekt.mobile.wear.features.ping.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.wear.features.ping.presentation.PingComponent

@Composable
fun PingScreen(
    pingComponent: PingComponent,
    modifier: Modifier = Modifier
) {
    val model by pingComponent.model.collectAsState()
    PingComposableScreen(
        model = model,
        modifier = modifier
    )
}
