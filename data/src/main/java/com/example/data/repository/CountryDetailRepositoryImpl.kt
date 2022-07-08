package com.example.data.repository

import com.apollographql.apollo3.ApolloClient
import com.example.CountryDetailQuery
import com.example.data.Constants
import com.example.data.mapers.toDomain
import com.example.domain.common.Result
import com.example.domain.common.asResult
import com.example.domain.models.CountryDetail
import com.example.domain.repository.CountryDetailRepository
import javax.inject.Inject

class CountryDetailRepositoryImpl @Inject constructor(
//    private val client: ApolloClient
) : CountryDetailRepository {

    override suspend fun getCountryDetail(code: String): Result<CountryDetail> {
        return asResult {
            val client = ApolloClient.Builder()
                .serverUrl(Constants.BASE_URL)
                .build()
            val data = client.query(CountryDetailQuery(code = code)).execute().data
            data.let { it?.country?.toDomain() }!!
        }
    }

}