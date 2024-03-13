package com.example.ytapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.ytapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
            }
        }

        binding.btnPlayMusic.setOnClickListener {
            val serviceIntent = Intent(this, MusicService::class.java).apply {
                action = ServiceAction.PLAY_MUSIC.toString()
            }
            startService(serviceIntent)
        }

        binding.btnStopPlayingMusic.setOnClickListener {
            val serviceIntent = Intent(this, MusicService::class.java).apply {
                action = ServiceAction.STOP_MUSIC.toString()
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(serviceIntent)
            } else {
                startService(serviceIntent)
            }
        }

        binding.btnDestroyService.setOnClickListener {
            val serviceIntent = Intent(this, MusicService::class.java)
            stopService(serviceIntent)
        }

    }

}
