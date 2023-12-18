package com.example.lesson_12_rudomanov.data.di

import com.example.lesson_12_rudomanov.data.remote.repository.BridgesRepository
import com.example.lesson_12_rudomanov.data.remote.repository.BridgesRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideBridgesRepository(
        bridgesRepository: BridgesRepositoryImpl,
    ): BridgesRepository
}