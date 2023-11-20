package com.example.lesson_7_rudomanov.data

import retrofit2.http.GET
import com.example.lesson_7_rudomanov.data.model.Bridge

interface MainApiService {
    @GET("bridges")
    suspend fun getBridges(): List<Bridge>?
}