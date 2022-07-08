package com.example.data.di

import com.example.data.repository.CountryDetailRepositoryImpl
import com.example.data.repository.CountryListRepositoryImpl
import com.example.domain.repository.CountryDetailRepository
import com.example.domain.repository.CountryListRepository
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        NetworkModule::class
    ]
)
interface RepositoryModule {

    @Binds
    fun getCountryListRepository(repositoryImpl: CountryListRepositoryImpl): CountryListRepository

    @Binds
    fun getCountryDetailRepository(repositoryImpl: CountryDetailRepositoryImpl): CountryDetailRepository

}