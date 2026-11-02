package com.makeevrserg.empireprojekt.mobile

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.makeevrserg.empireprojekt.mobile.App.Companion.asEmpireApp
import com.makeevrserg.empireprojekt.mobile.features.status.api.root.component.RootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.api.url.model.UrlStatusState
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.status.model.Status
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.status.util.STATUS_WEAR_MESSAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

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
    private val rootStatusComponent: RootStatusComponent
        get() = rootModule.statusModule.rootStatusComponent

    private suspend fun sendStatus() {
        coroutineScope {
            val messages = rootStatusComponent.statusComponents.map { statusComponent ->
                launch(Dispatchers.Main) { statusComponent.checkStatus() }
                val model = statusComponent.model.value
                Status(
                    title = model.title.toString(applicationContext),
                    isLoading = model.isLoading,
                    status = when (model.status) {
                        UrlStatusState.LoadingStatus.LOADING -> Status.LoadingStatus.LOADING
                        UrlStatusState.LoadingStatus.SUCCESS -> Status.LoadingStatus.SUCCESS
                        UrlStatusState.LoadingStatus.ERROR -> Status.LoadingStatus.ERROR
                    }
                )
            }
            wearMessengerModule.wearMessageProducer.produce(
                message = STATUS_WEAR_MESSAGE,
                value = messages
            )
        }
    }

    override suspend fun doWork(): Result {
        return coroutineScope {
            Log.d(TAG, "doWork: ")
            launch { sendStatus() }
            Result.success()
        }
    }

    companion object {
        private const val TAG = "CheckStatusWork"
        val DELAY = 10.seconds
    }
}
