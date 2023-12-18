package com.example.lesson_12_rudomanov.data

import com.example.lesson_12_rudomanov.data.remote.model.ApiBridge
import retrofit2.http.GET
import retrofit2.http.Path

interface MainApiService {
    @GET("bridges")
    suspend fun getBridges(): List<ApiBridge>

    @GET("bridges/{id}")
    suspend fun getBridge(@Path("id") id: Int): ApiBridge
}