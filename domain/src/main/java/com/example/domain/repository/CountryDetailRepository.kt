package com.example.domain.repository

import com.example.domain.common.Result
import com.example.domain.models.CountryDetail

interface CountryDetailRepository {

    suspend fun getCountryDetail(code: String): Result<CountryDetail>

}