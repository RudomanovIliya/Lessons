package com.example.lesson_9_rudomanov.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.example.lesson_9_rudomanov.data.ApiClient
import com.example.lesson_9_rudomanov.data.model.Weather
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class WeatherService : Service() {

    private val scope = CoroutineScope(Dispatchers.Main)
    private var weather: Weather? = null
    private var serviceCallbacks: ServiceCallbacks? = null

    inner class LocalBinder : Binder() {
        val service: WeatherService
            get() = this@WeatherService
    }

    override fun onBind(intent: Intent?): IBinder? {
        return LocalBinder()
    }

    override fun onCreate() {
        scope.launch {
            while (true) {
                weather = ApiClient.apiService.getWeather()
                serviceCallbacks?.showWeather(weather)
                delay(1000 * 60)
            }
        }
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    fun setCallbacks(callbacks: ServiceCallbacks?) {
        serviceCallbacks = callbacks
    }

    override fun onDestroy() {
        scope.cancel()
        super.onDestroy()
    }
}