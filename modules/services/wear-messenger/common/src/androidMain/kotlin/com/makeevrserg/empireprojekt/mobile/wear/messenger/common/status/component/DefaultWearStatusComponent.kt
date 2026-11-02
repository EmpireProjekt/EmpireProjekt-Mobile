package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.status.component

import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.api.WearMessageConsumer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.model.DecodedWearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.status.component.WearStatusComponent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.status.model.Status
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.status.model.WearStatusState
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.status.util.STATUS_WEAR_MESSAGE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlin.time.Clock

internal class DefaultWearStatusComponent(
    wearMessageConsumer: WearMessageConsumer,
    coroutineScope: CoroutineScope,
    private val clock: Clock
) : WearStatusComponent {
    override val mergedState: StateFlow<WearStatusState> = wearMessageConsumer.messagesFlow
        .filterIsInstance<DecodedWearMessage<Nothing>>()
        .filter { message -> message.path == STATUS_WEAR_MESSAGE.path }
        .filterIsInstance<DecodedWearMessage<List<Status>>>()
        .map { message -> toModel(message.value) }
        .stateIn(
            coroutineScope,
            SharingStarted.Eagerly,
            WearStatusState(
                loadingCount = 0,
                successCount = 0,
                failureCount = 0,
                updatedAt = null
            )
        )

    private fun toModel(statusModels: List<Status>): WearStatusState {
        return WearStatusState(
            loadingCount = statusModels.count { status ->
                status.isLoading || status.status == Status.LoadingStatus.LOADING
            },
            successCount = statusModels.count { status -> status.status == Status.LoadingStatus.SUCCESS },
            failureCount = statusModels.count { status -> status.status == Status.LoadingStatus.ERROR },
            updatedAt = clock.now()
        )
    }
}
