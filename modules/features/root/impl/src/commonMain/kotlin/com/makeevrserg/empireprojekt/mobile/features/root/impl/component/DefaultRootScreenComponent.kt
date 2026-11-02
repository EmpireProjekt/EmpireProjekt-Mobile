package com.makeevrserg.empireprojekt.mobile.features.root.impl.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.makeevrserg.empireprojekt.mobile.features.root.api.api.RootRouter
import com.makeevrserg.empireprojekt.mobile.features.root.api.component.RootScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.root.api.di.RootComponentModule
import com.makeevrserg.empireprojekt.mobile.services.core.navigation.component.PopComponent
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer

internal class DefaultRootScreenComponent(
    componentContext: ComponentContext,
    serializersModule: SerializersModule,
    rootComponentModule: RootComponentModule,
) : RootScreenComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<RootRouter.Configuration>()

    override val childStack: Value<ChildStack<*, RootScreenComponent.ScreenChild>> = childStack(
        source = navigation,
        initialConfiguration = RootRouter.Configuration.Splash,
        handleBackButton = true,
        serializer = serializersModule.serializer(),
        childFactory = { configuration, childContext ->
            rootComponentModule.createRootScreenChild(
                configuration = configuration,
                componentContext = childContext,
                rootRouter = this
            )
        }
    )

    override val popModel: Value<PopComponent.Model> = childStack.map { stack ->
        PopComponent.Model(
            canPop = stack.backStack.isNotEmpty(),
            popAction = ::pop
        )
    }

    override fun push(configuration: RootRouter.Configuration) {
        navigation.pushNew(configuration)
    }

    override fun replaceCurrent(configuration: RootRouter.Configuration) {
        navigation.replaceCurrent(configuration)
    }

    override fun replaceAll(configuration: RootRouter.Configuration) {
        navigation.replaceAll(configuration)
    }

    override fun pop() {
        navigation.pop()
    }
}
