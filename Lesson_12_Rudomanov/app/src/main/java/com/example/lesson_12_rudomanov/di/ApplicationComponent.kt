package com.example.lesson_12_rudomanov.di

import com.example.lesson_12_rudomanov.presentation.BridgesApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiServiceModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
        AndroidSupportInjectionModule::class,
    ]
)
interface ApplicationComponent : AndroidInjector<BridgesApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: BridgesApplication): ApplicationComponent
    }
}