package com.makeevrserg.empireprojekt.mobile.features.root.pager

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.root.pager.data.LastBottomItemRepository
import com.makeevrserg.empireprojekt.mobile.features.root.pager.model.PagerBottomBarItem
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.klibs.mikro.core.util.mapStateFlow

@Suppress("LongParameterList")
internal class DefaultPagerComponent(
    componentContext: ComponentContext,
    private val lastBottomItemRepository: LastBottomItemRepository,

) : PagerComponent, ComponentContext by componentContext {

    private val mapChild by lazy {
        PagerComponent.Child.Map
    }

    private val menuChild by lazy {
        PagerComponent.Child.Menu
    }

    override val selectedBottomBarItem: StateFlow<PagerBottomBarItem>
        get() = lastBottomItemRepository.lastBottomItemIndex.cachedStateFlow

    override val selectedChild = selectedBottomBarItem.mapStateFlow {
        when (it) {
            PagerBottomBarItem.Menu -> menuChild
            PagerBottomBarItem.Map -> mapChild
        }
    }

    override fun select(item: PagerBottomBarItem) {
        lastBottomItemRepository.lastBottomItemIndex.save(item)
    }

    override fun selectMap() {
        select(PagerBottomBarItem.Map)
    }

    override fun selectMenu() {
        select(PagerBottomBarItem.Menu)
    }
}
