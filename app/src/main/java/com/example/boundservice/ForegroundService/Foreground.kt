package com.example.boundservice.ForegroundService

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.boundservice.Default.Default.TAG
import com.example.boundservice.R

class Foreground : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foregound)
    }
    fun startService(view: View) {
        Log.d(TAG, "startService: MainActivity")
        val serviceSongIntent: Intent =
            Intent(this@Foreground, ForegroundService::class.java)
        startForegroundService(serviceSongIntent)
    }

    fun stopService(view: View) {
        Log.d(TAG, "stopService: MainActivity")
        val serviceSongIntent: Intent =
            Intent(this@Foreground, ForegroundService::class.java)
        stopService(serviceSongIntent)
    }
}