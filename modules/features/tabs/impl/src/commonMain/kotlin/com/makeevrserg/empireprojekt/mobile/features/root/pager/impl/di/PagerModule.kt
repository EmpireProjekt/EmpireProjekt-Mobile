package com.makeevrserg.empireprojekt.mobile.features.root.pager.impl.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.root.api.screen.RootRouter
import com.makeevrserg.empireprojekt.mobile.features.root.pager.impl.DefaultPagerComponent
import com.makeevrserg.empireprojekt.mobile.features.root.pager.impl.PagerComponent
import com.makeevrserg.empireprojekt.mobile.features.root.pager.impl.data.LastBottomItemRepositoryImpl
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule

interface PagerModule {
    fun createPagerComponent(
        componentContext: ComponentContext,
        onRootNavigation: (RootRouter.Configuration) -> Unit
    ): PagerComponent

    class Default(
        private val coreModule: CoreModule
    ) : PagerModule {
        override fun createPagerComponent(
            componentContext: ComponentContext,
            onRootNavigation: (RootRouter.Configuration) -> Unit
        ): PagerComponent = DefaultPagerComponent(
            componentContext = componentContext,
            lastBottomItemRepository = LastBottomItemRepositoryImpl(coreModule.settings),
        )
    }
}
