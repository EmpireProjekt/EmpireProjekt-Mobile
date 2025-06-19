package com.makeevrserg.empireprojekt.mobile.features.root.pager

import com.makeevrserg.empireprojekt.mobile.features.root.pager.model.PagerBottomBarItem
import kotlinx.coroutines.flow.StateFlow

interface PagerComponent {
    val selectedBottomBarItem: StateFlow<PagerBottomBarItem>
    val selectedChild: StateFlow<Child>

    fun select(item: PagerBottomBarItem)

    fun selectMenu()

    fun selectMap()

    sealed interface Child {
        data object Menu : Child

        data object Map : Child
    }
}
