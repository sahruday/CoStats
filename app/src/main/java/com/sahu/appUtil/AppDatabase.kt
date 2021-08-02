package com.sahu.appUtil

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sahu.costats.data.cache.dao.*


const val DB_NAME = "CoStats.db"

@Database(
    entities = [CountryStats::class, ProvinceStats::class, HistoricStats::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun countryDao() : CountryStatsDao

    abstract fun provinceDoa() : ProvinceDao

    abstract fun historicDao() : HistoricDao

}