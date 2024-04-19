package com.makeevrserg.empireprojekt.mobile.work

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.makeevrserg.empireprojekt.mobile.application.App.Companion.asEmpireApp
import com.makeevrserg.empireprojekt.mobile.features.status.url.presentation.UrlStatusComponent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.message.StatusModelMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.model.StatusModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import ru.astrainteractive.klibs.kdi.Provider
import ru.astrainteractive.klibs.kdi.getValue

class CheckStatusWork(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {
    private val wearMessengerModule by lazy {
        applicationContext.asEmpireApp().wearMessengerModule
    }
    private val rootModule by lazy {
        applicationContext.asEmpireApp().rootModule
    }
    private val rootStatusComponent by Provider {
        rootModule.statusModule.rootStatusComponent
    }

    override suspend fun doWork(): Result {
        Log.d(TAG, "doWork: ")
        sendStatus()
        return Result.success()
    }

    private suspend fun sendStatus() = coroutineScope {
        val messages = rootStatusComponent.statusComponents.map {
            async {
                withContext(Dispatchers.Main) { it.checkStatus() }
                val model = it.model.value
                StatusModel(
                    title = model.title.toString(applicationContext),
                    isLoading = model.isLoading,
                    status = when (model.status) {
                        UrlStatusComponent.LoadingStatus.LOADING -> StatusModel.LoadingStatus.LOADING
                        UrlStatusComponent.LoadingStatus.SUCCESS -> StatusModel.LoadingStatus.SUCCESS
                        UrlStatusComponent.LoadingStatus.ERROR -> StatusModel.LoadingStatus.ERROR
                    }
                )
            }
        }.awaitAll()
        wearMessengerModule.wearMessageProducer.produce(
            message = StatusModelMessage.Message,
            value = messages
        )
    }

    companion object {
        private const val TAG = "CheckStatusWork"
    }
}
