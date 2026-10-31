package com.makeevrserg.empireprojekt.mobile.features.rating.impl.user.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.empireprojekt.mobile.features.rating.api.user.component.RatingUserComponent

internal class DefaultRatingUserComponent(
    componentContext: ComponentContext,
    createRatingUserFeature: () -> RatingUserFeature
) : RatingUserComponent,
    ComponentContext by componentContext {
    private val ratingUserFeature = instanceKeeper.getOrCreate {
        createRatingUserFeature.invoke()
    }

    override val model = ratingUserFeature.model

    override fun reset() {
        ratingUserFeature.reset()
    }

    override fun loadNextPage() {
        ratingUserFeature.loadNextPage()
    }
}
