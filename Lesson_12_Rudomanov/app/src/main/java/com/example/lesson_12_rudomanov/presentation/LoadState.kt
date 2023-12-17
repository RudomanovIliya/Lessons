package com.example.lesson_12_rudomanov.presentation

sealed class LoadState<T> {
    class Loading<T> : LoadState<T>()
    data class Data<T>(val data: T) : LoadState<T>()
    data class Error<T>(val exception: Exception) : LoadState<T>()
}