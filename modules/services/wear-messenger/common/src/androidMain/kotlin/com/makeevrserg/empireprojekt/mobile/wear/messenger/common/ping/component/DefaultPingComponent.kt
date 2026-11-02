package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.ping.component

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.ping.component.PingComponent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.component.PingFeature
import com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.model.PingState
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.klibs.mikro.core.util.mapStateFlow

internal class DefaultPingComponent(
    componentContext: ComponentContext,
    pingFeature: PingFeature
) : ComponentContext by componentContext, PingComponent {

    override val model: StateFlow<PingComponent.Model> = pingFeature.model.mapStateFlow { state ->
        when (state) {
            is PingState.Connected -> PingComponent.Model.Success(updatedAt = state.timestamp)
            is PingState.NotConnected -> PingComponent.Model.NoConnection(updatedAt = state.timestamp)
        }
    }
}
