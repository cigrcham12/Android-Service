package com.example.boundservice.Application

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.example.boundservice.Default.Default.CHANNEL_ID

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationVersion()
    }

    private fun createNotificationVersion() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            var serviceChannel:NotificationChannel = NotificationChannel(
                CHANNEL_ID,
                "Test Channel Notification",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager:NotificationManager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }
}