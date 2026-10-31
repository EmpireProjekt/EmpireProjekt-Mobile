package com.makeevrserg.empireprojekt.mobile.features.ui.rating.user.composable.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.makeevrserg.empireprojekt.mobile.features.rating.api.user.component.RatingUserComponent
import com.makeevrserg.empireprojekt.mobile.services.core.navigation.component.PopComponent

@Composable
fun RatingUserScreenHostComposable(
    popComponent: PopComponent,
    ratingUserComponent: RatingUserComponent,
    modifier: Modifier = Modifier
) {
    val model by ratingUserComponent.model.collectAsState()
    val popModel by popComponent.popModel.subscribeAsState()
    RatingUserScreenComposable(
        model = model,
        onBack = popModel.popActionOrNull,
        onLoadNextPage = ratingUserComponent::loadNextPage,
        onReset = ratingUserComponent::reset,
        modifier = modifier
    )
}
