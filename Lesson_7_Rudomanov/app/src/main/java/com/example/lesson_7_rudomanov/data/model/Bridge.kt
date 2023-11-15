package com.example.lesson_7_rudomanov.data.model

import com.google.gson.annotations.SerializedName
import java.util.Objects

class Bridge(
    @SerializedName("name") val name: String?,
    @SerializedName("name_eng") val nameEng: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("description_eng") val descriptionEng: String?,
    @SerializedName("divorces") val divorces: List<Divorces>?,
    @SerializedName("lat") val lat: Float?,
    @SerializedName("lng") val lng: Float?,
    @SerializedName("photo_close_url") val photoCloseUrl: String?,
    @SerializedName("photo_open_url") val photoOpenUrl: String?,
    @SerializedName("public") val public: Boolean?,
)
