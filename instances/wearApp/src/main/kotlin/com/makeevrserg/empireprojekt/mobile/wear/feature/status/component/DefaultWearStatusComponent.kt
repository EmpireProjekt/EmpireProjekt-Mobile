package com.makeevrserg.empireprojekt.mobile.wear.feature.status.component

import com.makeevrserg.empireprojekt.mobile.features.status.url.component.UrlStatusComponent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.model.Status
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.util.STATUS_WEAR_MESSAGE
import com.makeevrserg.empireprojekt.mobile.wear.messenger.message.api.WearMessageConsumer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.message.model.DecodedWearMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DefaultWearStatusComponent(
    wearMessageConsumer: WearMessageConsumer,
    coroutineScope: CoroutineScope
) : WearStatusComponent {
    override val mergedState: StateFlow<WearStatusComponent.Model> =
        wearMessageConsumer.messagesFlow
            .filterIsInstance<DecodedWearMessage<Nothing>>()
            .filter { it.path == STATUS_WEAR_MESSAGE.path }
            .filterIsInstance<DecodedWearMessage<List<Status>>>()
            .map { it.value }
            .map { statusModels ->
                toModel(statusModels)
            }.stateIn(coroutineScope, SharingStarted.Eagerly, WearStatusComponent.Model())

    private fun toModel(statusModels: List<Status>): WearStatusComponent.Model {
        return WearStatusComponent.Model(
            loadingCount = statusModels.count {
                it.isLoading || it.status == Status.LoadingStatus.LOADING
            },
            successCount = statusModels.count { it.status == Status.LoadingStatus.SUCCESS },
            failureCount = statusModels.count { it.status == Status.LoadingStatus.ERROR },
            updatedAt = getTimeStamp()
        )
    }

    private fun getTimeStamp(): String {
        val lDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        val formatted = lDateTime.format(formatter)
        return formatted ?: "..."
    }

    override fun update(status: UrlStatusComponent.LoadingStatus, amount: Int) {
        // todo
    }
}
