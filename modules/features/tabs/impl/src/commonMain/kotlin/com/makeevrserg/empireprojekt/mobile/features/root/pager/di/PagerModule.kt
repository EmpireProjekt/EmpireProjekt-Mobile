package com.makeevrserg.empireprojekt.mobile.features.root.pager.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.root.pager.DefaultPagerComponent
import com.makeevrserg.empireprojekt.mobile.features.root.pager.PagerComponent
import com.makeevrserg.empireprojekt.mobile.features.root.pager.data.LastBottomItemRepositoryImpl
import com.makeevrserg.empireprojekt.mobile.features.root.screen.RootRouter
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
