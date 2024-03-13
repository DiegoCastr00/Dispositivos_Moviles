package com.example.ytapplication

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.ytapplication.R

class MusicService : Service() {

    private var mediaPlayer: MediaPlayer? = null
    private var currentPosition: Int = 0

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
            ServiceAction.PLAY_MUSIC.toString() -> playMusic()
            ServiceAction.STOP_MUSIC.toString() -> stopMusic()
            ServiceAction.STOP_SERVICE.toString() -> onDestroy()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun playMusic() {
        // Create a notification
        val notification = createNotification()

        // Start foreground service
        startForeground(1, notification)

        // Perform the service task here (e.g., play music)
        // Initialize MediaPlayer and start playing music
        mediaPlayer = MediaPlayer.create(this, R.raw.ejemplo)
        mediaPlayer?.seekTo(currentPosition) // Restore the position
        mediaPlayer?.start()
    }

    private fun stopMusic() {
        mediaPlayer?.pause()
        currentPosition = mediaPlayer?.currentPosition ?: 0
    }

    private fun createNotification(): Notification {
        val builder = NotificationCompat.Builder(this, "MY_SERVICE_CHANNEL")
            .setContentTitle("Music Player")
            .setContentText("Estas escuchando tu musica favorita!")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setSilent(true)

        return builder.build()
    }

    override fun onDestroy() {
        stopAndReleaseMediaPlayer()
        super.onDestroy()
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    private fun stopAndReleaseMediaPlayer() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
