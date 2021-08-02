package com.sahu.costats.data.cache.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(primaryKeys = ["country"])
data class CountryStats(
    val continent: String,
    val country: String,
    val cases: Int,//
    val deaths: Int,//
    val recovered: Int,//
    val active: Int,
    val critical: Int,
    val todayCases: Int,//
    val todayDeaths: Int,//
    val todayRecovered: Int,//
    val casesPerMillion: Float,//
    val deathsPerMillion: Float,//
    val recoveredPerMillion: Float,//
    val activePerMillion: Float,
    val criticalPerMillion: Float,
    val population: Int,
    val tests: Int,
    val testsPerMillion: Int,
    val oneCasePerPeople: Int,//
    val oneDeathPerPeople: Int,//
    val oneTestPerPeople: Int,//
    val flagUrl: String,
)


@Dao
interface CountryStatsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCountryData(stats: CountryStats)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCountriesData(allStats: List<CountryStats>)

    @Query("SELECT * FROM CountryStats")
    fun getAllCountriesStats(): Flow<List<CountryStats>>

    @Query("SELECT * FROM CountryStats WHERE country= :country")
    fun getCountriesStatsWithCountryName(country: String): Flow<List<CountryStats>>

    @Query("SELECT * FROM CountryStats WHERE continent= :continent")
    fun getCountriesStatsContinentWith(continent: String): Flow<List<CountryStats>>

    @Query("DELETE FROM CountryStats WHERE 1=1")
    suspend fun delete()

}