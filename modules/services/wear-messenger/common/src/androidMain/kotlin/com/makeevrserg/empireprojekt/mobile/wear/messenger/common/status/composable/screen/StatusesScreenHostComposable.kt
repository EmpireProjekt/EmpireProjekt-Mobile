package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.status.composable.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.status.component.WearStatusComponent

@Composable
fun StatusesScreenHostComposable(
    wearStatusComponent: WearStatusComponent,
    modifier: Modifier = Modifier
) {
    val mergedState by wearStatusComponent.mergedState.collectAsState()
    StatusesScreenComposable(
        model = mergedState,
        modifier = modifier
    )
}
