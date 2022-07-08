package com.example.data.database

import androidx.room.*

@Dao
interface CountryDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(countryData: CountryDetailEntity)

    @Update
    suspend fun update(countryData: CountryDetailEntity)

    @Query("SELECT * FROM ${CountryDatabase.DATABASE_NAME} WHERE code == :code")
    fun getCountryData(code: String): CountryDetailEntity
}