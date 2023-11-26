package com.example.lesson_9_rudomanov.data

import com.example.lesson_9_rudomanov.data.model.Weather
import retrofit2.http.GET

interface MainApiService {
    @GET("weather?q=saratov&units=metric&appid=a924f0f5b30839d1ecb95f0a6416a0c2")
    suspend fun getWeather(): Weather?
}