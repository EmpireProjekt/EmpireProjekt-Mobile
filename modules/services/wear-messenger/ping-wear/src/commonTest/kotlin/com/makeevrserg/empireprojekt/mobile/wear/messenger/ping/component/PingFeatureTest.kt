package com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.component

import com.makeevrserg.empireprojekt.mobile.wear.messenger.message.fake.FakeWearMessageConsumer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.message.model.DecodedWearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.fake.FakeClock
import com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.model.PingState
import com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.util.PING_TIMEOUT
import com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.util.PING_WEAR_MESSAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.time.Duration.Companion.seconds
import kotlin.time.Instant

@OptIn(ExperimentalCoroutinesApi::class)
class PingFeatureTest {
    private val start = Instant.parse("2026-01-01T00:00:00Z")
    private val clock = FakeClock(start)
    private val wearMessageConsumer = FakeWearMessageConsumer()

    private fun sendPing() {
        val ping = DecodedWearMessage(path = PING_WEAR_MESSAGE.path, value = 0.toByte())
        wearMessageConsumer.sendMessage(ping)
    }

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun GIVEN_no_ping_WHEN_created_THEN_not_connected_since_creation() {
        runTest {
            val pingFeature = PingFeature(wearMessageConsumer = wearMessageConsumer, clock = clock)

            val state = assertIs<PingState.NotConnected>(pingFeature.model.value)
            assertEquals(expected = start, actual = state.timestamp)
            pingFeature.cancel()
        }
    }

    @Test
    fun GIVEN_created_WHEN_ping_received_THEN_connected_at_receive_time() {
        runTest {
            val pingFeature = PingFeature(wearMessageConsumer = wearMessageConsumer, clock = clock)
            runCurrent()
            val receivedAt = start.plus(3.seconds)
            clock.now = receivedAt

            sendPing()
            runCurrent()

            val state = assertIs<PingState.Connected>(pingFeature.model.value)
            assertEquals(expected = receivedAt, actual = state.timestamp)
            pingFeature.cancel()
        }
    }

    @Test
    fun GIVEN_connected_WHEN_no_ping_within_timeout_THEN_not_connected() {
        runTest {
            val pingFeature = PingFeature(wearMessageConsumer = wearMessageConsumer, clock = clock)
            runCurrent()
            sendPing()
            runCurrent()
            val timedOutAt = start.plus(PING_TIMEOUT)
            clock.now = timedOutAt

            advanceTimeBy(PING_TIMEOUT.plus(1.seconds))

            val state = assertIs<PingState.NotConnected>(pingFeature.model.value)
            assertEquals(expected = timedOutAt, actual = state.timestamp)
            pingFeature.cancel()
        }
    }

    @Test
    fun GIVEN_connected_WHEN_pings_keep_coming_before_timeout_THEN_stays_connected() {
        runTest {
            val pingFeature = PingFeature(wearMessageConsumer = wearMessageConsumer, clock = clock)
            runCurrent()
            sendPing()
            runCurrent()

            advanceTimeBy(PING_TIMEOUT.minus(1.seconds))
            sendPing()
            runCurrent()
            advanceTimeBy(PING_TIMEOUT.minus(1.seconds))

            assertIs<PingState.Connected>(pingFeature.model.value)
            pingFeature.cancel()
        }
    }
}
