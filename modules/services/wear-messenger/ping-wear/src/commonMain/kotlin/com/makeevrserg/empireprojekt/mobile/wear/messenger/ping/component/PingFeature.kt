package com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.component

import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.api.WearMessageConsumer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.model.DecodedWearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.model.PingState
import com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.util.PING_TIMEOUT
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.time.Clock

class PingFeature(
    private val wearMessageConsumer: WearMessageConsumer,
    private val clock: Clock
) : CoroutineScope {
    override val coroutineContext = SupervisorJob() + Dispatchers.Main.immediate

    private val _model = MutableStateFlow<PingState>(PingState.NotConnected(clock.now()))
    val model = _model.asStateFlow()

    private fun launchDebounceJob() {
        _model
            .debounce(PING_TIMEOUT)
            .filterIsInstance<PingState.Connected>()
            .onEach { _model.value = PingState.NotConnected(clock.now()) }
            .launchIn(this)
    }

    private fun launchPingJob() {
        wearMessageConsumer.messagesFlow
            .filterIsInstance<DecodedWearMessage<Byte>>()
            .onEach { _model.value = PingState.Connected(clock.now()) }
            .launchIn(this)
    }

    init {
        launchPingJob()
        launchDebounceJob()
    }
}
