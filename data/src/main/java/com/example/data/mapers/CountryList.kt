package com.example.data.mapers

import com.example.CountryListQuery
import com.example.domain.models.CountriesItem

fun CountryListQuery.Country.toDomain(): CountriesItem {
    return CountriesItem(
        code = code,
        name = name,
        emoji = emoji
    )
}