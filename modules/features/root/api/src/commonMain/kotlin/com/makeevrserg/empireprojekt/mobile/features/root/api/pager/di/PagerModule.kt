package com.makeevrserg.empireprojekt.mobile.features.root.api.pager.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.root.api.pager.component.PagerComponent

interface PagerModule {
    fun createPagerComponent(componentContext: ComponentContext): PagerComponent
}
