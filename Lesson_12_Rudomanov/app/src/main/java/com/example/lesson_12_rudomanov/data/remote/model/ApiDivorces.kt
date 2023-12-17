package com.example.lesson_12_rudomanov.data.remote.model

import com.example.lesson_12_rudomanov.presentation.bridges.model.Divorces
import com.google.gson.annotations.SerializedName

class ApiDivorces(
    @SerializedName("start") val startTime: String?,
    @SerializedName("end") val endTime: String?
)

fun ApiDivorces.toModel(): Divorces {
    return Divorces(
        startTime = startTime.orEmpty(), endTime = endTime.orEmpty()
    )
}