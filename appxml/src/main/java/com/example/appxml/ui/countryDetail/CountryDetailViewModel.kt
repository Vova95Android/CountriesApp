package com.example.appxml.ui.countryDetail

import com.example.appxml.usecases.GetCountryDetailUseCase
import com.example.base_components.viewmodels.BaseViewModel
import com.example.domain.common.onErrorValue
import com.example.domain.common.onSuccessValue
import com.example.domain.models.CountryDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class CountryDetailViewModel @Inject constructor(
    private val getCountryDetailUseCase: GetCountryDetailUseCase
): BaseViewModel() {

    private val _countryDetail = MutableStateFlow<CountryDetail?>(null)
    val countryDetail = _countryDetail.asStateFlow()

    fun selectCountry(code: String) {
        launch {
            val data = getCountryDetailUseCase.loadCountries(code = code)
            data.onSuccessValue {
                _countryDetail.emit(it)
            }
            data.onErrorValue {
                it.printStackTrace()
            }
        }
    }
}