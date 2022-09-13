package com.example.boundservice.MessageBoundService

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
import android.view.View
import com.example.boundservice.R


class Message : AppCompatActivity() {
    private val MSG_PLAY_MUSIC:Int = 1
    private val TAG = "Cigrcham"
    // Message for communicating with the service
    private var mService:Messenger? = null
    // Flag indicating whether we have called bind on the service
    private var bound:Boolean = false
    // Class for interacting the main interface of the service

    private val mConnection = object : ServiceConnection{
        // This is called when the connection with the service has been established, giving us the object we can use to interact with the service.
        // We are communicating with the service using a Messenger, so here we get a client-side representation of that from the raw IBinder object.
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mService = Messenger(service)
            sendMessagePlayMusic()
            bound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            // This is called when the connection with the service has been unexpectedly disconnected-- that is, its process crashed
            mService = null
            bound = false

        }

    }
    private fun sendMessagePlayMusic() {
        var message:Message = Message.obtain(null, 1, 0, 0)
        mService?.send(message)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)
        Log.d(TAG, "onCreate: Message")

    }

    public fun onClickStartServiceMessage(view:View) {
        Log.d(TAG, "onClickStartServiceMessage: Message")
        Intent(this@Message, MessageService::class.java).also { it ->
          bindService(it, mConnection, Context.BIND_AUTO_CREATE)

      }
    }

    public fun onClickStopServiceMessage(view:View) {
        Log.d(TAG, "onClickStopServiceMessage: Message")
        if(bound){
            unbindService(mConnection)
            bound = false
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Message")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Message")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: Message")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Message")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: Message")
    }
}