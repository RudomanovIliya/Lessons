package com.example.lesson_12_rudomanov.data.di

import androidx.lifecycle.ViewModel
import com.example.lesson_12_rudomanov.presentation.bridges.InfoBridgeViewModel
import com.example.lesson_12_rudomanov.presentation.bridges.ListBridgesViewModel
import com.example.lesson_12_rudomanov.presentation.map.MapViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ListBridgesViewModel::class)
    abstract fun listBridgesViewModel(viewModel: ListBridgesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InfoBridgeViewModel::class)
    abstract fun infoBridgeViewModel(viewModel: InfoBridgeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    abstract fun mapViewModel(viewModel: MapViewModel): ViewModel
}