package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CountryDetailEntity::class], version = 1, exportSchema = false)
abstract class CountryDatabase : RoomDatabase() {
    abstract val countryDatabaseDao: CountryDetailDao

    companion object {
        const val DATABASE_NAME = "country_data"
    }
}