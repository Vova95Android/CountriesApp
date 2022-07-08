package com.example.countriesapp.usecases

import com.example.data.repository.CountryDetailRepositoryImpl
import com.example.domain.common.Result
import com.example.domain.common.onSuccessValue
import com.example.domain.models.CountryDetail
import com.example.domain.repository.DatabaseRepository
import javax.inject.Inject

class GetCountryDetailUseCase @Inject constructor(
    private val countryDetailRepository: CountryDetailRepositoryImpl,
    private val databaseRepository: DatabaseRepository
) {

    suspend fun loadCountries(code: String): Result<CountryDetail> {
        val databaseData = databaseRepository.getCountryData(code)
        return if (databaseData is Result.Success) databaseData
        else {
            val result = countryDetailRepository.getCountryDetail(code = code)
            result.onSuccessValue { databaseRepository.insertCountryData(it) }
            result
        }
    }

}