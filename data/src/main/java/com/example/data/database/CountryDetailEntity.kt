package com.example.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CountryDatabase.DATABASE_NAME)
data class CountryDetailEntity(
    @PrimaryKey(autoGenerate = false)
    val code: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "emoji")
    val emoji: String,
    @ColumnInfo(name = "capital")
    val capital: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "currency")
    val currency: String,
    @ColumnInfo(name = "continent")
    val continent: String,
    @ColumnInfo(name = "language")
    val language: String
)
