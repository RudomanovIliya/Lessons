package com.example.lesson_12_rudomanov.data.remote.repository

import com.example.lesson_12_rudomanov.data.MainApiService
import com.example.lesson_12_rudomanov.data.remote.model.toModel
import com.example.lesson_12_rudomanov.presentation.bridges.model.Bridge
import javax.inject.Inject

class BridgesRepositoryImpl @Inject constructor(
    private val apiService: MainApiService,
) : BridgesRepository {

    override suspend fun getBridges(): List<Bridge> {
        return apiService.getBridges().map { it.toModel() }
    }

    override suspend fun getBridge(id: Int): Bridge {
        return apiService.getBridge(id).toModel()
    }
}