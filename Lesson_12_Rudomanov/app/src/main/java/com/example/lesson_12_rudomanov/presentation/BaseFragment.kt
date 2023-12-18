package com.example.lesson_12_rudomanov.presentation

import androidx.annotation.LayoutRes
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import com.example.lesson_12_rudomanov.data.di.DaggerViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment(@LayoutRes layoutRes: Int) : DaggerFragment(layoutRes) {

    @Inject
    lateinit var daggerViewModelFactory: DaggerViewModelFactory

    protected inline fun <reified VM : ViewModel> appViewModels() =
        createViewModelLazy(VM::class, { viewModelStore }) {
            daggerViewModelFactory
        }
}