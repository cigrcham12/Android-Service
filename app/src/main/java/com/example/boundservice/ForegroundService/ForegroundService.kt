package com.example.boundservice.ForegroundService

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.boundservice.Default.Default.CHANNEL_ID
import com.example.boundservice.Default.Default.TAG
import com.example.boundservice.Model.Song
import com.example.boundservice.R


class ForegroundService: Service() {
    private lateinit var song: Song
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var remoteView: RemoteViews
    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind: ServiceSongNotification")
        return null
    }
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ServiceSongNotification")
        song = Song("Hai muoi nam", "Cigrcham", R.drawable.my_pic, R.raw.haimuoinam)
        remoteView = RemoteViews(packageName, R.layout.layout_custom_song_row)

    }
    private fun createRemoteView(song: Song) {
        val bitmap = BitmapFactory.decodeResource(resources, song.image)
        remoteView.setTextViewText(R.id.textViewSongTitle, song.title)
        remoteView.setTextViewText(R.id.textViewSingSong, song.single)
        remoteView.setImageViewBitmap(R.id.img_Song, bitmap)

    }
    private fun createMusic(songCurrent:Song) {
        Log.d(TAG, "createMusic: ServiceSongNotification")
        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.haimuoinam)
        mediaPlayer.setVolume(100f,200f)
        mediaPlayer.isLooping = true
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ServiceSongNotification")

        startForeground(1, sendNotificationMusic(song))
        startMusic()


        return START_NOT_STICKY
    }
    private fun startMusic() {
        Log.d(TAG, "startMusic: ServiceSongNotification")
        mediaPlayer.start()
        remoteView.setImageViewResource(R.id.imgPlayAndPause, R.drawable.play)
    }
    private fun sendNotificationMusic(songCurrent: Song): Notification {
        Log.d(TAG, "sendNotificationMusic: ServiceSongNotification")
        val notificationIntent: Intent =
            Intent(this@ForegroundService, Foreground::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            0
        )
        createMusic(songCurrent)
        createRemoteView(songCurrent)
        val bitmap = BitmapFactory.decodeResource(resources, song.image)
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Title")
            .setContentText("Text")
            .setSmallIcon(R.drawable.music)
            .setContentIntent(pendingIntent)
            .setCustomContentView(remoteView)
            .setContentTitle("Title")
            .setSound(null)
            .build()
    }
    override fun onDestroy() {
        super.onDestroy()
        stopMusic()
        Log.d(TAG, "onDestroy: ServiceSongNotification")
        stopSelf()
    }
    private fun stopMusic() {
        Log.d(TAG, "stopMusic: ServiceSongNotification")
        remoteView.setImageViewResource(R.id.imgPlayAndPause, R.drawable.play)
        mediaPlayer.stop()
    }
    fun pauseAndPlayMusic(){
        if(mediaPlayer.isPlaying){
            mediaPlayer.pause()
            remoteView.setImageViewResource(R.id.imgPlayAndPause, R.drawable.play)
        }
        else{
            mediaPlayer.pause()
            remoteView.setImageViewResource(R.id.imgPlayAndPause, R.drawable.pause)
        }
    }
}