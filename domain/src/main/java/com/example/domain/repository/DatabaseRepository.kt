package com.example.domain.repository

import com.example.domain.common.Result
import com.example.domain.models.CountryDetail

interface DatabaseRepository {

    suspend fun insertCountryData(data: CountryDetail)

    suspend fun getCountryData(code: String): Result<CountryDetail>

}