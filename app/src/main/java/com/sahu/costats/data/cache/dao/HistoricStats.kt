package com.sahu.costats.data.cache.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(primaryKeys = ["country", "date"])
data class HistoricStats(
    val country: String,
    val date: String,
    val cases: Int,
    val deaths: Int,
    val recovered: Int,
)


@Dao
interface HistoricDao {

    @Query("SELECT * FROM HistoricStats")
    fun getData() : Flow<List<HistoricStats>>

    @Query("SELECT * FROM HistoricStats WHERE country= :country")
    fun getData(country: String) : Flow<List<HistoricStats>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(list: List<HistoricStats>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(data: HistoricStats)

    @Query("DELETE FROM HistoricStats WHERE 1=1")
    fun delete()
}