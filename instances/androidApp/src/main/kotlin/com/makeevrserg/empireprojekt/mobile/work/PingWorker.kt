package com.makeevrserg.empireprojekt.mobile.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.makeevrserg.empireprojekt.mobile.application.App
import com.makeevrserg.empireprojekt.mobile.application.App.Companion.asEmpireApp
import com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.util.PING_WEAR_MESSAGE
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class PingWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {
    private val app: App
        get() = applicationContext.asEmpireApp()

    private suspend fun sendPing() {
        app.wearMessengerModule.wearMessageProducer.produce(
            message = PING_WEAR_MESSAGE,
            value = 0.toByte()
        )
    }

    override suspend fun doWork(): Result = coroutineScope {
        launch { sendPing() }
        Result.success()
    }
}
