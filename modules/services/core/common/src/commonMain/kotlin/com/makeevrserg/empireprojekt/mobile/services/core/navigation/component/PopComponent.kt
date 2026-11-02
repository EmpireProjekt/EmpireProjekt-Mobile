package com.makeevrserg.empireprojekt.mobile.services.core.navigation.component

import com.arkivanov.decompose.value.Value

interface PopComponent {
    val popModel: Value<Model>

    fun pop()

    class Model(canPop: Boolean, popAction: () -> Unit) {
        val popActionOrNull = popAction.takeIf { canPop }
    }
}
