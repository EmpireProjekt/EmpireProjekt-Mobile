package com.makeevrserg.empireprojekt.mobile

import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.ServiceInfo
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import androidx.work.ListenableWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.makeevrserg.empireprojekt.mobile.App.Companion.asEmpireApp
import com.makeevrserg.empireprojekt.mobile.modules.services.core.resources.R
import com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.util.PING_INTERVAL
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class StatusService : LifecycleService() {
    private val dispatcher by lazy {
        application.asEmpireApp().rootModule.coreModule.dispatchers.IO
    }
    private val intentOpen by lazy {
        PendingIntent.getActivity(
            applicationContext,
            0,
            Intent(applicationContext, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )
    }
    private val intentStop by lazy {
        PendingIntent.getService(
            applicationContext,
            0,
            Intent(applicationContext, StatusService::class.java).setAction(ACTION_STOP),
            PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun <T : ListenableWorker> startWork(clazz: Class<T>) {
        val request = OneTimeWorkRequest.Builder(clazz).build()
        val instanceWorkManager = WorkManager.getInstance(applicationContext)
        instanceWorkManager.enqueue(request)
    }

    private fun initPingWork() {
        lifecycleScope.launch(dispatcher) {
            while (isActive) {
                delay(PING_INTERVAL)
                startWork(PingWorker::class.java)
            }
        }
    }

    private fun initStatusWork() {
        lifecycleScope.launch(dispatcher) {
            while (isActive) {
                delay(CheckStatusWork.DELAY)
                startWork(CheckStatusWork::class.java)
            }
        }
    }

    private fun createNotificationChannel() {
        NotificationManagerCompat.from(applicationContext).createNotificationChannel(
            NotificationChannelCompat.Builder(CHANNEL_ID, NotificationManagerCompat.IMPORTANCE_MIN)
                .setName("Connection status")
                .build()
        )
    }

    private fun createNotification(): Notification {
        createNotificationChannel()
        // Make sure we are foreground.
        return NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle("Empire Wear")
            .setContentText("Wear sync is running")
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .setSmallIcon(R.drawable.img_splash)
            .setColor(Color.TRANSPARENT)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    applicationContext.resources,
                    R.drawable.img_splash
                )
            )
            .setContentIntent(intentOpen)
            .setDeleteIntent(intentStop)
            .addAction(
                R.drawable.img_splash,
                "Stop",
                intentStop
            )
            .setOngoing(true)
            .setSilent(true)
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .setBadgeIconType(NotificationCompat.BADGE_ICON_NONE)
            .setLocalOnly(true)
            .setShowWhen(false)
            .build()
    }

    private fun startNotification() {
        val notification = createNotification()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startForeground(
                SERVICE_ID,
                notification,
                ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
            )
        } else {
            startForeground(SERVICE_ID, notification)
        }
    }

    private fun stopSelfInternal() {
        lifecycleScope.launch {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                stopForeground(STOP_FOREGROUND_REMOVE)
            }
            stopSelf()
        }
    }

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {
        super.onStartCommand(
            intent,
            flags,
            startId
        )
        Log.d(TAG, "onStartCommand: ")
        if (intent?.action == ACTION_STOP) {
            stopSelfInternal()
        }
        return START_STICKY
    }

    override fun onCreate() {
        Log.d(TAG, "onCreate: ")
        startNotification()
        initStatusWork()
        initPingWork()
        super.onCreate()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        lifecycleScope.cancel()
        super.onDestroy()
    }

    override fun stopService(name: Intent?): Boolean {
        Log.d(TAG, "stopService: ")
        return super.stopService(name)
    }

    companion object {
        private const val TAG = "StatusService"
        private const val CHANNEL_ID = "connection"
        private const val SERVICE_ID = 100
        private const val ACTION_STOP = "com.makeevrserg.empireprojekt.mobile.work.StatusService.STOP"
    }
}
