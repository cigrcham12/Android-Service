package com.example.boundservice.ExtendClassBinder

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.example.boundservice.R


class BoundService: Service() {
    private var mediaPlayer:MediaPlayer? = null
    private val TAG = "Cigrcham"
    private var mBinder = LocalBinder()

    public fun startMediaPlayer(){
        if (mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(applicationContext, R.raw.haimuoinam)
        }
        mediaPlayer?.start()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startMediaPlayer()
        return START_STICKY
    }


   inner class LocalBinder:Binder(){
       fun getService(): BoundService = this@BoundService
   }


    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind: BoundService")
       return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind: BoundService")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: BoundService")
        if(mediaPlayer != null){
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: BoundService")
    }


}