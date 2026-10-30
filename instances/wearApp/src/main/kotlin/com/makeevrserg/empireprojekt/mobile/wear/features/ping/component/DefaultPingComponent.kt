package com.makeevrserg.empireprojekt.mobile.wear.features.ping.component

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.component.PingFeature
import com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.model.PingState
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.klibs.mikro.core.util.mapStateFlow
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.TimeZone

class DefaultPingComponent(
    componentContext: ComponentContext,
    pingFeature: PingFeature,
) : ComponentContext by componentContext, PingComponent {

    override val model: StateFlow<PingComponent.Model> = pingFeature.model.mapStateFlow {
        when (it) {
            is PingState.Connected -> PingComponent.Model.Success(
                updatedAt = getTimeStamp(it.timestamp.toEpochMilliseconds())
            )

            is PingState.NotConnected -> PingComponent.Model.NoConnection(
                updatedAt = getTimeStamp(it.timestamp.toEpochMilliseconds())
            )
        }
    }

    private fun getTimeStamp(millis: Long): String {
        val instant = Instant.ofEpochMilli(millis)
        val lDateTime = LocalDateTime.ofInstant(
            instant,
            TimeZone.getDefault().toZoneId()
        )
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        val formatted = lDateTime.format(formatter)
        return formatted ?: "..."
    }
}
