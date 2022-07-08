package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.CountryDatabase
import com.example.data.database.CountryDetailDao
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {

    @Provides
    fun provideDatabase(context: Context): CountryDetailDao {
        var INSTANCE: CountryDatabase? = null
        synchronized(this) {
            var instance = INSTANCE
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    CountryDatabase::class.java,
                    CountryDatabase.DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
            }
            return instance.countryDatabaseDao
        }
    }
}