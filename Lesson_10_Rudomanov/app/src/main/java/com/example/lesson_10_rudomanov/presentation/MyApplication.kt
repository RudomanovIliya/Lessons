package com.example.lesson_10_rudomanov.presentation

import android.app.Application
import com.example.lesson_10_rudomanov.R
import com.yandex.mapkit.MapKitFactory

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey(getString(R.string.yandex_key))
        MapKitFactory.initialize(this)
    }
}