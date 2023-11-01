package com.example.lesson_4_rudomanov

import android.graphics.drawable.Drawable
import android.media.Image

data class DetailInfoItem(
    var img: Drawable?,
    val title: String,
    val prompt: String,
)