package com.example.countriesapp.usecases

import com.example.data.repository.CountryDetailRepositoryImpl
import com.example.domain.common.Result
import com.example.domain.models.CountryDetail
import javax.inject.Inject

class GetCountryDetailUseCase @Inject constructor(
    private val countryDetailRepository: CountryDetailRepositoryImpl
) {

    suspend fun loadCountries(code: String): Result<CountryDetail> {
        return countryDetailRepository.getCountryDetail(code = code)
    }

}