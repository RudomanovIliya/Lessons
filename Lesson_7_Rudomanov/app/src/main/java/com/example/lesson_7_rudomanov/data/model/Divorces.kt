package com.example.lesson_7_rudomanov.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Divorces(
    val start: String?,
    val end: String?,
) : Parcelable