package com.example.data.mapers

import com.example.CountryDetailQuery
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
        languages = language,
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