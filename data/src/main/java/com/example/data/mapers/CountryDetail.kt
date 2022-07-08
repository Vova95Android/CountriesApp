package com.example.data.mapers

import com.example.CountryDetailQuery
import com.example.data.database.CountryDetailEntity
import com.example.domain.models.ContentData
import com.example.domain.models.CountryDetail

fun CountryDetailQuery.Country.toDomain(): CountryDetail {
    val language = languages.firstOrNull()?.name ?: ""
    return CountryDetail(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "",
        phone = phone,
        currency = currency ?: "",
        continent = continent.name,
        language = language,
        listData = listOf(
            Pair(ContentData.Continent, continent.name),
            Pair(ContentData.Capital, capital ?: ""),
            Pair(ContentData.Code, code),
            Pair(ContentData.Phone, phone),
            Pair(ContentData.Currency, currency ?: ""),
            Pair(ContentData.Language, language)
        )
    )
}

fun CountryDetailEntity.toDomain(): CountryDetail {
    return CountryDetail(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital,
        phone = phone,
        currency = currency,
        continent = continent,
        language = language,
        listData = listOf(
            Pair(ContentData.Continent, continent),
            Pair(ContentData.Capital, capital),
            Pair(ContentData.Code, code),
            Pair(ContentData.Phone, phone),
            Pair(ContentData.Currency, currency),
            Pair(ContentData.Language, language)
        )
    )
}

fun CountryDetail.toEntity(): CountryDetailEntity {
    return CountryDetailEntity(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital,
        phone = phone,
        currency = currency,
        continent = continent,
        language = language
    )
}