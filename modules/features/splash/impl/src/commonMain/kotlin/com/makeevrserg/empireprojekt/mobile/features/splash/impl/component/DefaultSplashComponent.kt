package com.makeevrserg.empireprojekt.mobile.features.splash.impl.component

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.root.api.splash.component.SplashComponent
import com.makeevrserg.empireprojekt.mobile.features.splash.impl.api.SplashComponentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

internal class DefaultSplashComponent(
    context: ComponentContext,
    mainScope: CoroutineScope,
    dispatchers: KotlinDispatchers,
    repository: SplashComponentRepository
) : SplashComponent,
    ComponentContext by context {
    private val _screenChannel = Channel<SplashComponent.Label>()
    override val screenChannelFlow = _screenChannel.consumeAsFlow()

    init {
        mainScope.launch(dispatchers.IO) {
            val isInitialLaunch = repository.isInitialLaunch()
            val label = SplashComponent.Label.InitialLaunch(isInitialLaunch)
            _screenChannel.send(label)
        }
    }
}
