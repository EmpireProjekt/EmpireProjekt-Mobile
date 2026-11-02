package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.ping.composable.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.ping.component.PingComponent

@Composable
fun PingScreenHostComposable(
    pingComponent: PingComponent,
    modifier: Modifier = Modifier
) {
    val model by pingComponent.model.collectAsState()
    PingScreenComposable(
        model = model,
        modifier = modifier
    )
}
