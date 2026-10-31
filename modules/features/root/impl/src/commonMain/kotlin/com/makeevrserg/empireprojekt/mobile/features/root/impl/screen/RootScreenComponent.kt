package com.makeevrserg.empireprojekt.mobile.features.root.impl.screen

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import com.makeevrserg.empireprojekt.mobile.features.root.api.screen.RootRouter

interface RootScreenComponent : BackHandlerOwner, RootRouter {
    val childStack: Value<ChildStack<*, DefaultRootScreenComponent.Configuration>>
}
