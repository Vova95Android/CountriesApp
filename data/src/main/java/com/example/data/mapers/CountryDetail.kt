package com.example.data.mapers

import com.example.CountryDetailQuery
import com.example.CountryListQuery
import com.example.domain.models.CountriesItem
import com.example.domain.models.CountryDetail

fun CountryDetailQuery.Country.toDomain(): CountryDetail {
    return CountryDetail(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital?: "",
        phone = phone,
        currency = currency?: "",
        continent = continent.name,
        languages = languages.firstOrNull()?.name?: "",
    )
}