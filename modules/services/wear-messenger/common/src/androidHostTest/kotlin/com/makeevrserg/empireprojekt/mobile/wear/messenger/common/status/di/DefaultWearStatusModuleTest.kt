@file:Suppress("FunctionNaming")

package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.status.di

import android.content.ComponentName
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.ResolveInfo
import androidx.wear.protolayout.ActionBuilders
import androidx.wear.protolayout.DeviceParametersBuilders
import androidx.wear.protolayout.LayoutElementBuilders
import androidx.wear.protolayout.material.CompactChip
import androidx.wear.protolayout.material.layouts.PrimaryLayout
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.api.WearMessageConsumer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.model.DecodedWearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.model.WearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.status.component.WearStatusComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.emptyFlow
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.time.Clock

@OptIn(ExperimentalHorologistApi::class)
@RunWith(RobolectricTestRunner::class)
class DefaultWearStatusModuleTest {
    private fun renderLayout(context: Context): PrimaryLayout {
        val wearMessageConsumer = object : WearMessageConsumer {
            override val messagesFlow = emptyFlow<DecodedWearMessage<*>>()

            override suspend fun <T> consume(message: WearMessage<T>, byteArray: ByteArray) = Unit
        }
        val module = DefaultWearStatusModule(
            wearMessageConsumer = wearMessageConsumer,
            coroutineScope = CoroutineScope(SupervisorJob()),
            clock = Clock.System,
            context = context
        )
        val state = WearStatusComponent.Model(
            loadingCount = 2,
            successCount = 3,
            failureCount = 1,
            updatedAt = null
        )
        val deviceParameters = DeviceParametersBuilders.DeviceParameters.Builder()
            .setScreenWidthDp(SCREEN_SIZE_DP)
            .setScreenHeightDp(SCREEN_SIZE_DP)
            .build()
        val renderedTile = module.mainTileRenderer.renderTile(state, deviceParameters)
        return assertNotNull(PrimaryLayout.fromLayoutElement(renderedTile))
    }

    @Test
    fun GIVEN_launcher_alias_WHEN_tile_is_rendered_THEN_launch_action_targets_alias_and_package() {
        val context = RuntimeEnvironment.getApplication()
        val launcher = ComponentName(context.packageName, "${context.packageName}.LauncherAlias")
        val packageManager = shadowOf(context.packageManager)
        val activityInfo = packageManager.addActivityIfNotPresent(launcher)
        activityInfo.exported = true
        packageManager.setResolveInfosForIntent(
            Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER).setPackage(context.packageName),
            listOf(ResolveInfo().apply { this.activityInfo = activityInfo })
        )

        val layout = renderLayout(context)
        val chip = assertNotNull(CompactChip.fromLayoutElement(assertNotNull(layout.primaryChipContent)))
        val launchAction = assertNotNull(chip.clickable.onClick) as ActionBuilders.LaunchAction
        val activity = assertNotNull(launchAction.androidActivity)

        assertEquals(launcher.packageName, activity.packageName)
        assertEquals(launcher.className, activity.className)
    }

    @Test
    fun GIVEN_no_launcher_WHEN_tile_is_rendered_THEN_statuses_remain_without_more_chip() {
        val context = object : ContextWrapper(RuntimeEnvironment.getApplication()) {
            override fun getPackageName(): String = "com.example.no.launcher"
        }

        val layout = renderLayout(context)
        val content = assertNotNull(layout.content) as LayoutElementBuilders.Column
        val statuses = content.contents.last() as LayoutElementBuilders.Row

        assertNull(layout.primaryChipContent)
        assertEquals(EXPECTED_STATUS_ROW_ITEMS, statuses.contents.size)
    }

    private companion object {
        const val SCREEN_SIZE_DP = 200
        const val EXPECTED_STATUS_ROW_ITEMS = 5
    }
}
