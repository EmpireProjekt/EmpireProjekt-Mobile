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
    fun testInitialLoadingState() {
        runTest {
            val urlStatusComponent = createUrlStatusComponent { true }
            urlStatusComponent.model.test {
                assertEquals(UrlStatusComponent.LoadingStatus.LOADING, awaitItem().status)
            }
        }
    }

    @Test
    fun testSuccessState() {
        runTest {
            val urlStatusComponent = createUrlStatusComponent { true }
            urlStatusComponent.model.test {
                skipItems(1)
                assertEquals(UrlStatusComponent.LoadingStatus.SUCCESS, awaitItem().status)
            }
        }
    }

    @Test
    fun testFailureState() {
        runTest {
            val urlStatusComponent = createUrlStatusComponent { false }
            urlStatusComponent.model.test {
                skipItems(1)
                assertEquals(UrlStatusComponent.LoadingStatus.ERROR, awaitItem().status)
            }
        }
    }

    @Test
    fun testFailureWithExceptionState() {
        runTest {
            val urlStatusComponent = createUrlStatusComponent { error("Hello world") }
            urlStatusComponent.model.test {
                skipItems(1)
                assertEquals(UrlStatusComponent.LoadingStatus.ERROR, awaitItem().status)
            }
        }
    }
}
