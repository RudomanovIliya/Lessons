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
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_9_rudomanov.service.DownloadService
import com.example.lesson_9_rudomanov.R
import com.example.lesson_9_rudomanov.service.ServiceCallbacks
import com.example.lesson_9_rudomanov.service.WeatherService
import com.example.lesson_9_rudomanov.data.model.Weather
import com.example.lesson_9_rudomanov.databinding.ActivityMainBinding
import java.io.File
import java.io.FileInputStream

class MainActivity : AppCompatActivity(), ServiceCallbacks {
    private val binding by viewBinding(ActivityMainBinding::bind)
    private var weatherService: WeatherService? = null
    private var myBinder: WeatherService.LocalBinder? = null
    private val serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            myBinder = service as? WeatherService.LocalBinder
            weatherService = myBinder?.service
            weatherService!!.setCallbacks(this@MainActivity)
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            myBinder = null
        }
    }

    private val fileReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val fileName = intent?.getStringExtra(DownloadService.KEY_FILE)
            val f = File(filesDir.path, fileName)
            val b = BitmapFactory.decodeStream(FileInputStream(f))
            val img = binding.imageViewDownload
            img.setImageBitmap(b)
            binding.buttonDownload.visibility = View.VISIBLE
            binding.progresBar.visibility = View.GONE
        }
    }
    private val progressReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            binding.progressBarDownload.progress =
                intent?.getIntExtra(DownloadService.KEY_PROGRESS, 0)!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, WeatherService::class.java)
        bindService(intent, serviceConnection, BIND_AUTO_CREATE)
        setContentView(R.layout.activity_main)
        binding.buttonDownload.setOnClickListener {
            binding.progresBar.visibility = View.VISIBLE
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
            binding.buttonDownload.visibility = View.GONE
        }
        registerReceiver(fileReceiver, IntentFilter(FILE_RECEIVER))
        registerReceiver(progressReceiver, IntentFilter(PROGRESS_RECEIVER))
        //большой файл
        //https://drive.google.com/uc?export=download&id=1xArwgS2Os2TMh_xn_nwZUjWTAwqYxjJj
        //маленький файл
        //https://drive.google.com/uc?export=download&id=1k5a0dKgAqc6zWfoPkNYNHIGjkNL6QNuh
        //средний файл
        //https://drive.google.com/uc?export=download&id=1sPq06DG4EvZVKf76mkYUd94-hhErUwV-
    }

    override fun onDestroy() {
        if (myBinder != null) {
            weatherService?.setCallbacks(null)
            unbindService(serviceConnection)
        }
        unregisterReceiver(fileReceiver)
        unregisterReceiver(progressReceiver)
        super.onDestroy()
    }

    override fun showWeather(weather: Weather?) {
        try {
            if (weather?.mainInfo?.temperature != null)
                binding.textViewTemperatura.text = (weather.mainInfo.temperature).toString()
            else binding.textViewTemperatura.text = getString(R.string.no_data)
        } catch (e: Exception) {
            Log.d("LinkWeather", e.message!!)
        }
    }

    companion object {
        const val FILE_RECEIVER =
            "com.example.lesson_9_rudomanov.presentation.FAIL_RECEIVER"
        const val PROGRESS_RECEIVER =
            "com.example.lesson_9_rudomanov.presentation.PROGRESS_RECEIVER"
    }
}