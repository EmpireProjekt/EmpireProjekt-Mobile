package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.root.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.root.component.WearRootComponent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.root.di.WearRootComponentModule
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.root.model.WearRootConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer

internal class DefaultWearRootComponent(
    componentContext: ComponentContext,
    serializersModule: SerializersModule,
    wearRootComponentModule: WearRootComponentModule
) : WearRootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<WearRootConfiguration>()

    override val childStack: Value<ChildStack<*, WearRootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = WearRootConfiguration.Main,
        serializer = serializersModule.serializer(),
        handleBackButton = true,
        childFactory = wearRootComponentModule::createRootChild
    )

    override fun openStatuses() {
        navigation.push(WearRootConfiguration.Statuses)
    }

    override fun openPing() {
        navigation.push(WearRootConfiguration.Ping)
    }

    override fun pop() {
        navigation.pop()
    }
}
