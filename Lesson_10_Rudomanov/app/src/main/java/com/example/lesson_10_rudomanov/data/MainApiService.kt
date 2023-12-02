package com.example.lesson_10_rudomanov.data

import com.example.lesson_10_rudomanov.data.model.Bridge
import retrofit2.http.GET

interface MainApiService {
    @GET("bridges")
    suspend fun getBridges(): List<Bridge>?
}