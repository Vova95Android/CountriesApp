package com.example.countriesapp.usecases

import com.example.domain.common.Result
import com.example.domain.models.CountriesItem
import com.example.domain.repository.CountryListRepository
import javax.inject.Inject

class GetCountryUseCase @Inject constructor(
    private val countryListRepository: CountryListRepository
) {

    suspend fun loadCountries(query: String?): Result<List<CountriesItem>> {
        return countryListRepository.getCountryList(query)
    }

}