package com.example.data.di

import com.example.data.repository.CountryDetailRepositoryImpl
import com.example.data.repository.CountryListRepositoryImpl
import com.example.data.repository.DatabaseRepositoryImpl
import com.example.domain.repository.CountryDetailRepository
import com.example.domain.repository.CountryListRepository
import com.example.domain.repository.DatabaseRepository
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
        DatabaseModule::class
    ]
)
interface RepositoryModule {

    @Binds
    fun getCountryListRepository(repositoryImpl: CountryListRepositoryImpl): CountryListRepository

    @Binds
    fun getCountryDetailRepository(repositoryImpl: CountryDetailRepositoryImpl): CountryDetailRepository

    @Binds
    fun getCountryDatabaseRepository(repositoryImpl: DatabaseRepositoryImpl): DatabaseRepository

}