package com.example.domain.repository

import com.example.domain.models.CountriesItem
import com.example.domain.common.Result

interface CountryListRepository {

    suspend fun getCountryList(query: String?): Result<List<CountriesItem>>

}