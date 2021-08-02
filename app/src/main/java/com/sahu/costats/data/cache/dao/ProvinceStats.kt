package com.sahu.costats.data.cache.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity
data class ProvinceStats(
    val country: String,
    @PrimaryKey val province: String,
    @Embedded val stats: Stats
)


data class Stats(
    val confirmed: Int,
    val deaths: Int,
    val recovered: Int
)

@Dao
interface ProvinceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addData(data: ProvinceStats)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addData(list: List<ProvinceStats>)

    @Query("SELECT * FROM ProvinceStats WHERE country= :country")
    fun getData(country: String): Flow<List<ProvinceStats>>

    @Query("SELECT * FROM ProvinceStats")
    fun getData(): Flow<List<ProvinceStats>>

    @Query("DELETE FROM ProvinceStats WHERE 1=1")
    fun delete()
}