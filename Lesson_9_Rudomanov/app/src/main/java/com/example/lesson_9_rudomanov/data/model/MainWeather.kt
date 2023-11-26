package com.example.lesson_9_rudomanov.data.model

import com.google.gson.annotations.SerializedName

class MainWeather(
    @SerializedName("temp") val temperature: Float?,
    val feelLike: Int?,
    val tempMin: Int?,
    val tempMax: Int?,
    val pressure: Int?,
    val humidity: Int?,
)