package com.example.domain.models

data class CountryDetail(
    val code: String,
    val name: String,
    val emoji: String,
    val capital: String,
    val phone: String,
    val currency: String,
    val continent: String,
    val languages: String,
    val listData: List<Pair<ContentData, String>>
)

enum class ContentData {
    Continent,
    Capital,
    Code,
    Phone,
    Currency,
    Language
}