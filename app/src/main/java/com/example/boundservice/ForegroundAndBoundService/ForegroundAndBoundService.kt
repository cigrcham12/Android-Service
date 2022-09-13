package com.example.boundservice.ForegroundAndBoundService

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import com.example.boundservice.R

class ForegroundAndBoundService : AppCompatActivity() {
    private lateinit var mService: ForeAndBoundService
    private var isServiceConnected = false



    private val connection = object : ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            var myBinder: ForeAndBoundService.ForeAndBoundBinder = service as ForeAndBoundService.ForeAndBoundBinder
            mService = myBinder.getService()
            isServiceConnected = true


        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isServiceConnected = false
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreground_and_bound_service)
    }
    public fun StartForeGroundServiceAndBoundServicee(view:View){
        val intent = Intent(this@ForegroundAndBoundService, ForeAndBoundService::class.java)
        startService(intent)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }
    public fun StopForeGroundServicee(view:View){
        val intent = Intent(this@ForegroundAndBoundService, ForeAndBoundService::class.java)
        stopService(intent)
    }
    public fun StopBoundGroundServicee(view:View){
        if(isServiceConnected){
            unbindService(connection)
        }
        isServiceConnected = false


    }
}