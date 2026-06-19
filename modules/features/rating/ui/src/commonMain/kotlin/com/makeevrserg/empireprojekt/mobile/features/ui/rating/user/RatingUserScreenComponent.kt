package com.makeevrserg.empireprojekt.mobile.features.ui.rating.user

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.makeevrserg.empireprojekt.mobile.features.rating.user.presentation.RatingUserComponent
import com.makeevrserg.empireprojekt.mobile.services.core.PopComponent

@Composable
fun RatingUserScreenComponent(
    popComponent: PopComponent,
    ratingUserComponent: RatingUserComponent,
    modifier: Modifier = Modifier
) {
    val model by ratingUserComponent.model.collectAsState()
    val popModel by popComponent.popModel.subscribeAsState()
    RatingUserComposableScreen(
        model = model,
        onBack = popModel.popActionOrNull,
        onLoadNextPage = ratingUserComponent::loadNextPage,
        onReset = ratingUserComponent::reset,
        modifier = modifier
    )
}
