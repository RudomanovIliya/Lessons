package com.example.lesson_9_rudomanov.service

import com.example.lesson_9_rudomanov.data.model.Weather

interface ServiceCallbacks {
    fun showWeather(weather: Weather?)
}