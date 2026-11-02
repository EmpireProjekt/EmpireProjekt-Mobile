package com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.fake

import kotlin.time.Clock
import kotlin.time.Instant

internal class FakeClock(var now: Instant) : Clock {
    override fun now(): Instant = now
}
