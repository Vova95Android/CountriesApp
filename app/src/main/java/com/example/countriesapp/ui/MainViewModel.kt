package com.example.countriesapp.ui

import com.example.base_components.viewmodels.BaseViewModel
import com.example.countriesapp.usecases.GetCountryDetailUseCase
import com.example.countriesapp.usecases.GetCountryUseCase
import com.example.domain.common.onErrorValue
import com.example.domain.common.onSuccessValue
import com.example.domain.models.CountriesItem
import com.example.domain.models.CountryDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCountryUseCase: GetCountryUseCase,
    private val getCountryDetailUseCase: GetCountryDetailUseCase
) : BaseViewModel() {

    private val _countryList = MutableStateFlow<List<CountriesItem>>(emptyList())
    val countryList = _countryList.asStateFlow()

    private val _countryDetail = MutableStateFlow<CountryDetail?>(null)
    val countryDetail = _countryDetail.asStateFlow()

    init {
        setQuery(null)
    }

    private val _query = MutableStateFlow<String?>(null)
    val query = _query.asStateFlow()

    fun selectCountry(code: String) {
        launch {
            val data = getCountryDetailUseCase.loadCountries(code = code)
            data.onSuccessValue {
                _countryDetail.emit(it)
                _countryList.emit(countryList.value.map {
                    if (it.code == code) it.copy(isSelected = true)
                    else it.copy(isSelected = false)
                })
            }
            data.onErrorValue {
                it.printStackTrace()
            }
        }
    }

    fun setQuery(query: String?) {
        launch {
            _query.emit(query)
            //TODO API does not have the ability to filter by country name
            val data = getCountryUseCase.loadCountries(query = null)
            data.onSuccessValue {
                val list = if (query.isNullOrEmpty()) it
                else it.filter { it.name.contains(query, ignoreCase = true) }
                if (list.isNotEmpty()) selectCountry(list.first().code)
                _countryList.emit(list)
            }
            data.onErrorValue {
                it.printStackTrace()
            }
        }
    }
}
