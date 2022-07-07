package com.example.data.repository

import com.apollographql.apollo3.ApolloClient
import com.example.CountryListQuery
import com.example.data.Constants
import com.example.data.mapers.toDomain
import com.example.domain.common.Result
import com.example.domain.common.asResult
import com.example.domain.models.CountriesItem
import com.example.domain.repository.CountryListRepository
import javax.inject.Inject

class CountryListRepositoryImpl @Inject constructor(
//    private val client: ApolloClient
): CountryListRepository {

    override suspend fun getCountryList(query: String?): Result<List<CountriesItem>> {
        return asResult {
            val client = ApolloClient.Builder()
                .serverUrl(Constants.BASE_URL)
                .build()
            val data = client.query(CountryListQuery()).execute().data?.countries?: emptyList()
            data.map { it.toDomain() }
        }
    }

}