package com.makeevrserg.empireprojekt.mobile.features.root.pager.impl.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.root.api.pager.component.PagerComponent
import com.makeevrserg.empireprojekt.mobile.features.root.api.pager.di.PagerModule
import com.makeevrserg.empireprojekt.mobile.features.root.pager.impl.component.DefaultPagerComponent
import com.makeevrserg.empireprojekt.mobile.features.root.pager.impl.krate.KrateLastBottomItemRepository
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule

class DefaultPagerModule(
    private val coreModule: CoreModule
) : PagerModule {
    override fun createPagerComponent(componentContext: ComponentContext): PagerComponent {
        return DefaultPagerComponent(
            componentContext = componentContext,
            lastBottomItemRepository = KrateLastBottomItemRepository(coreModule.settings),
        )
    }
}
