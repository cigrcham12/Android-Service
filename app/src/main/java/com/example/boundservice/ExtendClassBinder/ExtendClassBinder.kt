package com.example.boundservice.ExtendClassBinder

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import com.example.boundservice.R
import kotlinx.android.synthetic.main.activity_extend_class_binder.*

class ExtendClassBinder : AppCompatActivity() {
    private lateinit var mService: BoundService
    private var isServiceConnected = false

    private val connection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            isServiceConnected = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            var myBinder: BoundService.LocalBinder = service as BoundService.LocalBinder
            mService = myBinder.getService()
            mService.startMediaPlayer()
            isServiceConnected = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extend_class_binder)
        btn_Start_Service.setOnClickListener {
            onClickStartService()

        }
        btn_Stop_Service.setOnClickListener {
            onClickStopService()
        }
    }
    private fun onClickStartService() {
        val intent = Intent(this, BoundService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    private fun onClickStopService() {
        if(isServiceConnected){
            unbindService(connection)
            isServiceConnected = false
        }
    }

    public fun startMediaPlayer(){
        var mediaPlayer = MediaPlayer.create(applicationContext, R.raw.haimuoinam)
        if (mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(applicationContext, R.raw.haimuoinam)
        }
        mediaPlayer?.start()
    }
}