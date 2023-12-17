package com.example.lesson_12_rudomanov.data.remote.repository

import com.example.lesson_12_rudomanov.presentation.bridges.model.Bridge

interface BridgesRepository {

    /** Получить мосты */
    suspend fun getBridges(): List<Bridge>

    /** Получить мост */
    suspend fun getBridge(id: Int): Bridge
}