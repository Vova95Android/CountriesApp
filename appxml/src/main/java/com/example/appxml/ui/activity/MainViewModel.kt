package com.example.appxml.ui.activity

import com.example.base_components.viewmodels.BaseViewModel
import com.example.domain.models.CountriesItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {

    private val _activeCountry = MutableStateFlow<CountriesItem?>(null)
    val activeCountry = _activeCountry.asStateFlow()

    fun selectCountry(country: CountriesItem) {
        launch {
            _activeCountry.emit(country)
        }
    }
}