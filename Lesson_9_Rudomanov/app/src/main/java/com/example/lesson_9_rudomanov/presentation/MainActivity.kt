package com.example.lesson_9_rudomanov.presentation

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_9_rudomanov.service.DownloadService
import com.example.lesson_9_rudomanov.R
import com.example.lesson_9_rudomanov.service.ServiceCallbacks
import com.example.lesson_9_rudomanov.service.WeatherService
import com.example.lesson_9_rudomanov.service.WeatherService.LocalBinder
import com.example.lesson_9_rudomanov.data.model.Weather
import com.example.lesson_9_rudomanov.databinding.ActivityMainBinding
import java.io.File
import java.io.FileInputStream

class MainActivity : AppCompatActivity(), ServiceCallbacks {
    private var weatherService: WeatherService? = null
    private var bound = false
    private val binding by viewBinding(ActivityMainBinding::bind)

    private val fileReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val fileName = intent?.getStringExtra(DownloadService.KEY_FILE)
            val f: File = File(filesDir.path, fileName)
            val b = BitmapFactory.decodeStream(FileInputStream(f))
            val img = binding.imageViewDownload
            img.setImageBitmap(b)
            binding.buttonDownload.visibility=View.VISIBLE
            binding.progresBar.visibility=View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding.buttonDownload.setOnClickListener {
            binding.progresBar.visibility=View.VISIBLE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(
                    DownloadService.createStartIntent(
                        this,
                        "https://drive.google.com/uc?export=download&id=1sPq06DG4EvZVKf76mkYUd94-hhErUwV-"
                    )
                )
            } else {
                startService(
                    DownloadService.createStartIntent(
                        this,
                        "https://drive.google.com/uc?export=download&id=1sPq06DG4EvZVKf76mkYUd94-hhErUwV-"
                    )
                )
            }
            binding.buttonDownload.visibility=View.GONE
        }
        registerReceiver(fileReceiver, IntentFilter(FILE_RECEIVER))
        //большой файл
        //https://drive.google.com/uc?export=download&id=1xArwgS2Os2TMh_xn_nwZUjWTAwqYxjJj
        //маленький файл
        //https://drive.google.com/uc?export=download&id=1k5a0dKgAqc6zWfoPkNYNHIGjkNL6QNuh
        //средний файл
        //https://drive.google.com/uc?export=download&id=1sPq06DG4EvZVKf76mkYUd94-hhErUwV-
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, WeatherService::class.java)
        bindService(intent, serviceConnection, BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if (bound) {
            weatherService!!.setCallbacks(null) // unregister
            unbindService(serviceConnection)
            bound = false
        }
    }

    override fun onDestroy() {
        unregisterReceiver(fileReceiver)
        super.onDestroy()
    }

    private val serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as LocalBinder
            weatherService = binder.service
            bound = true
            weatherService!!.setCallbacks(this@MainActivity) // register
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            bound = false
        }
    }

    override fun showWeather(weather: Weather?) {
        binding.textViewTemperatura.text = (weather!!.mainInfo!!.temperature).toString()
    }

    companion object {
        const val FILE_RECEIVER =
            "com.example.lesson_9_rudomanov.presentation.FAIL_RECEIVER"
    }
}