package com.example.lesson_4_rudomanov

sealed class ListItem {

    data class DetailInfoItemSeal(val detail: DetailInfoItem) : ListItem()

    data class BaseInfoItemSeal(val base: BaseInfoItem) : ListItem()
}