package com.example.countriesapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.example.countriesapp.ui.MainViewModel
import com.example.base_components.viewmodels.ViewModelFactory
import com.example.base_components.viewmodels.ViewModelKey

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(value = MainViewModel::class)
    fun providesMainViewModel(model: MainViewModel): ViewModel
}