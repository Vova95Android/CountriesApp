package com.example.countriesapp.ui

import com.example.base_components.viewmodels.BaseViewModel
import com.example.data.usecases.GetCountryUseCase
import com.example.domain.common.onErrorValue
import com.example.domain.common.onSuccessValue
import com.example.domain.models.CountriesItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCountryUseCase: GetCountryUseCase
) : BaseViewModel() {

    private val _countryList = MutableStateFlow<List<CountriesItem>>(emptyList())
    val countryList = _countryList.asStateFlow()

    init {
        setQuery(null)
    }

    private val _query = MutableStateFlow<String?>(null)
    val query = _query.asStateFlow()

    fun setQuery(query: String?) {
        launch {
            _query.emit(query)
            val data = getCountryUseCase.loadCountries(query = query)
            data.onSuccessValue {
                _countryList.emit(it)
            }
            data.onErrorValue {
                it.printStackTrace()
            }
        }
    }


}