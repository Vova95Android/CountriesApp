package com.example.domain.models

data class CountriesItem(
    val code: String,
    val name: String,
    val emoji: String,
    val isSelected: Boolean = false
)
