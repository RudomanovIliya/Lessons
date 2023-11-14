package com.example.lesson_6_rudomanov

import android.text.SpannableString

data class InfoItem(
    var imageRes: Int,
    val title: String,
    val serialNumber: String,
    val isElectro: Boolean,
    val alert: String?,
    val alertSpan: SpannableString?,
)