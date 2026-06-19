package com.makeevrserg.empireprojekt.mobile.features.ui.status.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.features.status.url.presentation.UrlStatusComponent

@Composable
internal fun StatusWidget(
    statusComponent: UrlStatusComponent,
    modifier: Modifier = Modifier
) {
    val model by statusComponent.model.collectAsState()
    StatusWidgetContent(
        status = model.status,
        isLoading = model.isLoading,
        title = model.title,
        onClick = statusComponent::checkStatus,
        modifier = modifier
    )
}
