package com.example.boundservice.MainActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.boundservice.ExtendClassBinder.ExtendClassBinder
import com.example.boundservice.ForegroundAndBoundService.ForegroundAndBoundService
import com.example.boundservice.ForegroundService.Foreground
import com.example.boundservice.MessageBoundService.Message
import com.example.boundservice.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }
    public fun startExtendClassBinder(view:View){
        startActivity(Intent(this@MainActivity, ExtendClassBinder::class.java))
    }
    public fun startMessage(view: View){
        startActivity(Intent(this@MainActivity, Message::class.java))
    }
    public fun startForeGoundAndBoundSevice(view: View){
        startActivity(Intent(this@MainActivity, ForegroundAndBoundService::class.java))
    }
    public fun startForegroundService(view: View){
        startActivity(Intent(this@MainActivity, Foreground::class.java))
    }
}