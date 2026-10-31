package com.makeevrserg.empireprojekt.mobile.features.ui.status.composable.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.features.status.api.url.component.UrlStatusComponent

@Composable
internal fun StatusWidgetComposable(
    statusComponent: UrlStatusComponent,
    modifier: Modifier = Modifier
) {
    val model by statusComponent.model.collectAsState()
    StatusWidgetContentComposable(
        status = model.status,
        isLoading = model.isLoading,
        title = model.title,
        onClick = statusComponent::checkStatus,
        modifier = modifier
    )
}
