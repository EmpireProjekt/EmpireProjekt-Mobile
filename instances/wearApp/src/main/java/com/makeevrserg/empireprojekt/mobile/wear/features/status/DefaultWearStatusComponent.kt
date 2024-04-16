package com.makeevrserg.empireprojekt.mobile.wear.features.status

import com.makeevrserg.empireprojekt.mobile.features.status.url.UrlStatusComponent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.app.model.StatusModel
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.consumer.WearMessageConsumer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.DecodedWearMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
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
            .filterIsInstance<DecodedWearMessage<List<StatusModel>>>()
            .map { it.value }
            .map { statusModels ->

                WearStatusComponent.Model(
                    loadingCount = statusModels.count {
                        it.isLoading || it.status == StatusModel.LoadingStatus.LOADING
                    },
                    successCount = statusModels.count { it.status == StatusModel.LoadingStatus.SUCCESS },
                    failureCount = statusModels.count { it.status == StatusModel.LoadingStatus.ERROR },
                    updatedAt = getTimeStamp()
                )
            }.stateIn(coroutineScope, SharingStarted.Eagerly, WearStatusComponent.Model())

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
