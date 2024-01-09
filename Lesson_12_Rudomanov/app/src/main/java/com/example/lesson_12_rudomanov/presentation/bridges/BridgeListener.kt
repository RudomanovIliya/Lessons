package com.example.lesson_12_rudomanov.presentation.bridges

import com.example.lesson_12_rudomanov.presentation.bridges.model.Bridge

fun interface BridgeListener {
    fun onBridgeClick(bridge: Bridge)
}