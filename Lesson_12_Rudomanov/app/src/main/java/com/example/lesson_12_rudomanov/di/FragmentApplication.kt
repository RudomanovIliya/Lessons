package com.example.lesson_12_rudomanov.di

import com.example.lesson_12_rudomanov.presentation.bridges.InfoBridgeFragment
import com.example.lesson_12_rudomanov.presentation.bridges.ListBridgeFragment
import com.example.lesson_12_rudomanov.presentation.map.MapFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun listBridgeFragment(): ListBridgeFragment

    @ContributesAndroidInjector
    abstract fun infoBridgeFragment(): InfoBridgeFragment

    @ContributesAndroidInjector
    abstract fun MapFragment(): MapFragment
}