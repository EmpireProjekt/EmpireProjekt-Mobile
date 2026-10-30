package com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.model

import kotlin.time.Instant

sealed interface PingState {
    /**
     * @param timestamp last time when ping were received
     */
    class Connected(val timestamp: Instant) : PingState

    /**
     * @param timestamp last time when ping were received
     */
    class NotConnected(val timestamp: Instant) : PingState
}
