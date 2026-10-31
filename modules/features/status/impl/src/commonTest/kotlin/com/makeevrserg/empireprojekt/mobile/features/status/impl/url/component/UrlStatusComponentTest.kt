@file:Suppress("FunctionNaming")

package com.makeevrserg.empireprojekt.mobile.features.status.impl.url.component

import app.cash.turbine.test
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.makeevrserg.empireprojekt.mobile.features.status.api.url.component.UrlStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.api.UrlStatusRepository
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.mvikotlin.createUrlStatusStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import ru.astrainteractive.klibs.mikro.core.coroutines.propagateCancellationException
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class UrlStatusComponentTest {
    private fun createUrlStatusComponent(block: () -> Boolean): DefaultUrlStatusComponent {
        val urlStatusRepository = object : UrlStatusRepository {
            override suspend fun isActive(): Result<Boolean> {
                delay(200L)
                return runCatching { block.invoke() }.propagateCancellationException()
            }
        }
        return DefaultUrlStatusComponent(
            title = "TITLE",
            urlStatusStore = createUrlStatusStore(
                storeFactory = DefaultStoreFactory(),
                urlStatusRepository = urlStatusRepository
            )
        )
    }

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun GIVEN_reachable_url_WHEN_component_is_created_THEN_status_is_loading(): TestResult {
        return runTest {
            val urlStatusComponent = createUrlStatusComponent { true }
            urlStatusComponent.model.test {
                assertEquals(UrlStatusComponent.LoadingStatus.LOADING, awaitItem().status)
            }
        }
    }

    @Test
    fun GIVEN_reachable_url_WHEN_check_completes_THEN_status_is_success(): TestResult {
        return runTest {
            val urlStatusComponent = createUrlStatusComponent { true }
            urlStatusComponent.model.test {
                skipItems(1)
                assertEquals(UrlStatusComponent.LoadingStatus.SUCCESS, awaitItem().status)
            }
        }
    }

    @Test
    fun GIVEN_unreachable_url_WHEN_check_completes_THEN_status_is_error(): TestResult {
        return runTest {
            val urlStatusComponent = createUrlStatusComponent { false }
            urlStatusComponent.model.test {
                skipItems(1)
                assertEquals(UrlStatusComponent.LoadingStatus.ERROR, awaitItem().status)
            }
        }
    }

    @Test
    fun GIVEN_failing_request_WHEN_check_completes_THEN_status_is_error(): TestResult {
        return runTest {
            val urlStatusComponent = createUrlStatusComponent { error("Hello world") }
            urlStatusComponent.model.test {
                skipItems(1)
                assertEquals(UrlStatusComponent.LoadingStatus.ERROR, awaitItem().status)
            }
        }
    }
}
