package com.example.appxml.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appxml.ui.activity.MainViewModel
import com.example.appxml.ui.countryDetail.CountryDetailViewModel
import com.example.appxml.ui.countryList.CountryListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.example.base_components.viewmodels.ViewModelFactory
import com.example.base_components.viewmodels.ViewModelKey

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(value = CountryListViewModel::class)
    fun providesCountryListViewModel(model: CountryListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(value = CountryDetailViewModel::class)
    fun providesCountryDetailViewModel(model: CountryDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(value = MainViewModel::class)
    fun providesMainViewModel(model: MainViewModel): ViewModel
}