package com.sahu.costats.data.cache

import com.sahu.costats.data.cache.dao.*
import com.sahu.costats.data.remote.response.model.CountryHistoricalModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalService @Inject constructor(
    private val countryStatsDao: CountryStatsDao,
    private val provinceDao: ProvinceDao,
    private val historicDao: HistoricDao,
    //use mapper(s)
) {

    fun getData(): Flow<List<CountryStats>> = countryStatsDao.getAllCountriesStats()

    fun getProvinceData(country: String): Flow<List<ProvinceStats>> =
        if (country.isNullOrEmpty()) provinceDao.getData()
        else provinceDao.getData(country)

    fun getHistoricData(country: String): Flow<List<HistoricStats>> =
        if (country.isNullOrEmpty()) historicDao.getData()
        else historicDao.getData(country)

    suspend fun deleteAll() = countryStatsDao.delete()

    suspend fun insertData(list: List<CountryStats>) = countryStatsDao.addCountriesData(list)

    fun deleteAllProvinceData() = provinceDao.delete()

    fun insertProvinceData(list: List<ProvinceStats>) = provinceDao.addData(list)

    fun deleteAllHistoricData() = historicDao.delete()

    fun insertHistoricData(list: List<HistoricStats>) = historicDao.insertData(list)

}