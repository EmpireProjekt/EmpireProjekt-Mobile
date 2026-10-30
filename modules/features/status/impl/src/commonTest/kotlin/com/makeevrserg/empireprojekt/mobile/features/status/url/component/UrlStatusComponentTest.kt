@file:Suppress("FunctionNaming")

package com.makeevrserg.empireprojekt.mobile.features.status.url.component

import app.cash.turbine.test
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.makeevrserg.empireprojekt.mobile.features.status.url.data.UrlStatusRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class UrlStatusComponentTest {
    private fun createUrlStatusComponent(block: () -> Boolean): DefaultUrlStatusComponent {
        return DefaultUrlStatusComponent(
            title = "TITLE",
            storeFactory = DefaultStoreFactory(),
            urlStatusRepository = object : UrlStatusRepository {
                override suspend fun isActive(): Result<Boolean> {
                    delay(200L)
                    return runCatching { block.invoke() }
                }
            }
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
    fun GIVEN_reachable_url_WHEN_component_is_created_THEN_status_is_loading() {
        runTest {
            val urlStatusComponent = createUrlStatusComponent { true }
            urlStatusComponent.model.test {
                assertEquals(UrlStatusComponent.LoadingStatus.LOADING, awaitItem().status)
            }
        }
    }

    @Test
    fun GIVEN_reachable_url_WHEN_check_completes_THEN_status_is_success() {
        runTest {
            val urlStatusComponent = createUrlStatusComponent { true }
            urlStatusComponent.model.test {
                skipItems(1)
                assertEquals(UrlStatusComponent.LoadingStatus.SUCCESS, awaitItem().status)
            }
        }
    }

    @Test
    fun GIVEN_unreachable_url_WHEN_check_completes_THEN_status_is_error() {
        runTest {
            val urlStatusComponent = createUrlStatusComponent { false }
            urlStatusComponent.model.test {
                skipItems(1)
                assertEquals(UrlStatusComponent.LoadingStatus.ERROR, awaitItem().status)
            }
        }
    }

    @Test
    fun GIVEN_failing_request_WHEN_check_completes_THEN_status_is_error() {
        runTest {
            val urlStatusComponent = createUrlStatusComponent { error("Hello world") }
            urlStatusComponent.model.test {
                skipItems(1)
                assertEquals(UrlStatusComponent.LoadingStatus.ERROR, awaitItem().status)
            }
        }
    }
}
