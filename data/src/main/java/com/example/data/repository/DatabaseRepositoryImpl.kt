package com.example.data.repository

import com.example.data.database.CountryDetailDao
import com.example.data.mapers.toDomain
import com.example.data.mapers.toEntity
import com.example.domain.common.Result
import com.example.domain.common.asResult
import com.example.domain.models.CountryDetail
import com.example.domain.repository.DatabaseRepository
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val databaseDao: CountryDetailDao
): DatabaseRepository {

    override suspend fun insertCountryData(data: CountryDetail) {
        databaseDao.insert(data.toEntity())
    }

    override suspend fun getCountryData(code: String): Result<CountryDetail> {
        return asResult { databaseDao.getCountryData(code).toDomain() }
    }
}