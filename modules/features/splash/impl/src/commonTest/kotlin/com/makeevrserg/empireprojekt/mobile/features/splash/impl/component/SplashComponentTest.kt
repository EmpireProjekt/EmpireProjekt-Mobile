package com.makeevrserg.empireprojekt.mobile.features.splash.impl.component

import app.cash.turbine.test
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.makeevrserg.empireprojekt.mobile.features.root.api.splash.component.SplashComponent
import com.makeevrserg.empireprojekt.mobile.features.splash.impl.api.SplashComponentRepository
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.runTest
import ru.astrainteractive.klibs.mikro.core.dispatchers.DefaultKotlinDispatchers
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SplashComponentTest {
    private val componentContext = DefaultComponentContext(LifecycleRegistry())

    private fun createSplashComponent(isInitialLaunch: Boolean): DefaultSplashComponent {
        val repository = object : SplashComponentRepository {
            override fun isInitialLaunch(): Boolean {
                return isInitialLaunch
            }
        }
        return DefaultSplashComponent(
            context = componentContext,
            mainScope = MainScope(),
            dispatchers = DefaultKotlinDispatchers,
            repository = repository
        )
    }

    @Test
    fun GIVEN_initial_launch_WHEN_component_is_created_THEN_label_says_initial_launch(): TestResult {
        return runTest {
            val splashComponent = createSplashComponent(isInitialLaunch = true)

            splashComponent.screenChannelFlow.test {
                val item = awaitItem()
                assertTrue(item is SplashComponent.Label.InitialLaunch)
                assertEquals(true, item.value)
            }
        }
    }

    @Test
    fun GIVEN_not_initial_launch_WHEN_component_is_created_THEN_label_says_not_initial_launch(): TestResult {
        return runTest {
            val splashComponent = createSplashComponent(isInitialLaunch = false)

            splashComponent.screenChannelFlow.test {
                val item = awaitItem()
                assertTrue(item is SplashComponent.Label.InitialLaunch)
                assertEquals(false, item.value)
            }
        }
    }
}
