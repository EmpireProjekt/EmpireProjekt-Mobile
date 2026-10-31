package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.service

import android.util.Log
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.api.WearMessengerApplication
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.util.STATUS_WEAR_MESSAGE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

internal class StatusListenerService : WearableListenerService() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private suspend fun receiveStatusMessage(messageEvent: MessageEvent) {
        if (messageEvent.path != STATUS_WEAR_MESSAGE.path) {
            Log.d(TAG, "receiveStatusMessage: can't handle message ${messageEvent.path}")
            return
        }
        val wearMessengerApplication = application as? WearMessengerApplication
        if (wearMessengerApplication == null) {
            Log.e(TAG, "receiveStatusMessage: the application does not receive wear messages")
            return
        }
        wearMessengerApplication.wearMessageConsumer.consume(
            message = STATUS_WEAR_MESSAGE,
            byteArray = messageEvent.data
        )
    }

    override fun onMessageReceived(messageEvent: MessageEvent) {
        super.onMessageReceived(messageEvent)
        Log.d(TAG, "onMessageReceived: ${messageEvent.path}")
        scope.launch { receiveStatusMessage(messageEvent) }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    private companion object {
        const val TAG = "StatusListenerService"
    }
}
