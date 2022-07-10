package com.example.appxml.ui.countryList

import com.example.appxml.usecases.GetCountryUseCase
import com.example.base_components.viewmodels.BaseViewModel
import com.example.domain.common.onErrorValue
import com.example.domain.common.onSuccessValue
import com.example.domain.models.CountriesItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class CountryListViewModel @Inject constructor(
    private val getCountryUseCase: GetCountryUseCase
) : BaseViewModel() {

    private val _countryList = MutableStateFlow<List<CountriesItem>>(emptyList())
    val countryList = _countryList.asStateFlow()

    init {
        setQuery(null)
    }

    fun setQuery(query: String?) {
        launch {
            //TODO API does not have the ability to filter by country name
            val data = getCountryUseCase.loadCountries(query = null)
            data.onSuccessValue {
                val list = if (query.isNullOrEmpty()) it
                else it.filter { it.name.contains(query, ignoreCase = true) }
//                if (list.isNotEmpty()) selectCountry(list.first().code)
                _countryList.emit(list)
            }
            data.onErrorValue {
                it.printStackTrace()
            }
        }
    }

    fun selectCountry(code: String) {
        launch {
            _countryList.emit(countryList.value.map {
                if (it.code == code) it.copy(isSelected = true)
                else it.copy(isSelected = false)
            })
        }
    }
}