package com.makeevrserg.empireprojekt.mobile.features.towns.ui.composable.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.component.TownsComponent
import com.makeevrserg.empireprojekt.mobile.services.core.navigation.component.PopComponent

@Composable
fun TownsScreenHostComposable(
    popComponent: PopComponent,
    townsComponent: TownsComponent,
    modifier: Modifier = Modifier
) {
    val model by townsComponent.model.collectAsState()
    val popModel by popComponent.popModel.subscribeAsState()
    TownsScreenComposable(
        model = model,
        onBack = popModel.popActionOrNull,
        onLoadNextPage = townsComponent::loadNextPage,
        onReset = townsComponent::reset,
        onPublicTypeClick = townsComponent::nextPublicType,
        onSortByNameClick = townsComponent::nextNameSort,
        onSortByTagClick = townsComponent::nextTagSort,
        onSortByFounderClick = townsComponent::nextFounderSort,
        onSortByNationClick = townsComponent::nextNationSort,
        onSortByDateClick = townsComponent::nextDateSort,
        onSortByResidentsClick = townsComponent::nextResidentsSort,
        modifier = modifier
    )
}
