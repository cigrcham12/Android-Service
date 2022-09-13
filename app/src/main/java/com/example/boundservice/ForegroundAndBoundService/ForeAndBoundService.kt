package com.example.boundservice.ForegroundAndBoundService

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.boundservice.Default.Default.CHANNEL_ID
import com.example.boundservice.Default.Default.TAG
import com.example.boundservice.R


class ForeAndBoundService: Service() {
    private val myBinder: ForeAndBoundBinder = ForeAndBoundBinder()

    inner class ForeAndBoundBinder: Binder() {
        fun getService(): ForeAndBoundService = this@ForeAndBoundService
    }



    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind: ForeAndBoundService")


        return myBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind: ForeAndBoundService")
        return super.onUnbind(intent)
    }



    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ForeAndBoundService")
        sendNotification()
        return START_NOT_STICKY
    }

    private fun sendNotification() {

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
        builder.setContentTitle("Title")
            .setContentText("Text")
            .setSmallIcon(R.drawable.myphone)

        val notification:Notification = builder.build()

        startForeground(1, notification)
    }


    override fun onCreate() {
        Log.d(TAG, "onCreate: ForeAndBoundService")
        super.onCreate()
    }



    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ForeAndBoundService")
        stopSelf()
        super.onDestroy()
    }



}