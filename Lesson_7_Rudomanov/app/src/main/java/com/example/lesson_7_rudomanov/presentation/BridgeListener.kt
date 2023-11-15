package com.example.lesson_7_rudomanov.presentation

import com.example.lesson_7_rudomanov.data.model.Bridge

fun interface BridgeListener {
    fun onBridgeClick(bridge: Bridge,check: Int)
}