package com.example.boundservice.MessageBoundService

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
import android.widget.Toast
import com.example.boundservice.R

/** Command to the service to display a message  */
public const val MSG_SAY_HELLO = 1
public var mediaPlayer:MediaPlayer? = null
private val TAG = "Cigrcham"
private var mMessenger:Messenger? = null
class MessageService : Service() {
    public val MSG_PLAY_MUSIC = 1


    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    private lateinit var mMessenger: Messenger

    /**
     * Handler of incoming messages from clients.
     */
    internal class IncomingHandler(
        context: Context,
        private val applicationContext: Context = context.applicationContext
    ) : Handler() {
        override fun handleMessage(msg: Message) {
            Log.d(TAG, "handleMessage: MessageService")
            when (msg.what) {
                MSG_SAY_HELLO ->{
                    Toast.makeText(applicationContext, "Hai mươi năm!", Toast.LENGTH_SHORT).show()
                    StartMediaPlay()
                }
                else -> super.handleMessage(msg)
            }
        }

        private fun StartMediaPlay() {
            if(mediaPlayer == null){
                Log.d(TAG, "StartMediaPlay: MessageService")
                mediaPlayer = MediaPlayer.create(applicationContext, R.raw.haimuoinam)
            }
            mediaPlayer?.start()
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: MessageService")
    }

    /**
     * When binding to the service, we return an interface to our messenger
     * for sending messages to the service.
     */
    override fun onBind(intent: Intent): IBinder? {
        Log.d(TAG, "onBind: MessageService")
        mMessenger = Messenger(IncomingHandler(this))
        return mMessenger.binder
    }

    override fun unbindService(conn: ServiceConnection) {
        super.unbindService(conn)
        Log.d(TAG, "unbindService: MessageService")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: MessageService")
        if(mediaPlayer != null)
        {
            mediaPlayer?.release()
        }
        mediaPlayer = null
    }

}