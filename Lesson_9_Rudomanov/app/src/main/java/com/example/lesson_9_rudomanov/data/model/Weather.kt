package com.example.lesson_9_rudomanov.data.model

import com.google.gson.annotations.SerializedName

class Weather(
    @SerializedName("main") val mainInfo: MainWeather?,
)