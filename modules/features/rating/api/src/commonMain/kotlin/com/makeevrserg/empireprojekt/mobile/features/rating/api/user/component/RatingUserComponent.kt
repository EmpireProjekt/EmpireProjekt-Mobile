package com.makeevrserg.empireprojekt.mobile.features.rating.api.user.component

import com.makeevrserg.empireprojekt.mobile.features.rating.api.user.model.RatingUserState
import kotlinx.coroutines.flow.StateFlow

interface RatingUserComponent {
    val model: StateFlow<RatingUserState>

    fun reset()

    fun loadNextPage()
}
