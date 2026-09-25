package com.makeevrserg.empireprojekt.mobile.wear.features.status.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.wear.features.status.presentation.WearStatusComponent

@Composable
fun StatusesScreen(
    wearStatusComponent: WearStatusComponent,
    modifier: Modifier = Modifier
) {
    val mergedState by wearStatusComponent.mergedState.collectAsState()
    StatusesComposableScreen(
        model = mergedState,
        modifier = modifier
    )
}
