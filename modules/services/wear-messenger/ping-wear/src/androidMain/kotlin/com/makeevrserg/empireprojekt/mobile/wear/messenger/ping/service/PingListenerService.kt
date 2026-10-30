package com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.service

import android.util.Log
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService
import com.makeevrserg.empireprojekt.mobile.wear.messenger.message.api.WearMessengerApplication
import com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.util.PING_WEAR_MESSAGE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

internal class PingListenerService : WearableListenerService() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private suspend fun receivePingMessage(messageEvent: MessageEvent) {
        if (messageEvent.path != PING_WEAR_MESSAGE.path) {
            Log.d(TAG, "receivePingMessage: can't handle message ${messageEvent.path}")
            return
        }
        val wearMessengerApplication = application as? WearMessengerApplication
        if (wearMessengerApplication == null) {
            Log.e(TAG, "receivePingMessage: the application does not receive wear messages")
            return
        }
        wearMessengerApplication.wearMessageConsumer.consume(
            message = PING_WEAR_MESSAGE,
            byteArray = messageEvent.data
        )
    }

    override fun onMessageReceived(messageEvent: MessageEvent) {
        super.onMessageReceived(messageEvent)
        Log.d(TAG, "onMessageReceived: ${messageEvent.path}")
        scope.launch { receivePingMessage(messageEvent) }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    private companion object {
        const val TAG = "PingListenerService"
    }
}
