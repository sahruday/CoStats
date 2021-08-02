package com.sahu.costats.data

import android.util.Log.i
import com.sahu.appUtil.Callback
import com.sahu.costats.data.cache.LocalService
import com.sahu.costats.data.cache.dao.CountryStats
import com.sahu.costats.data.cache.dao.HistoricStats
import com.sahu.costats.data.cache.dao.ProvinceStats
import com.sahu.costats.data.remote.RemoteService
import com.sahu.costats.data.remote.response.mapper.toCountryStats
import com.sahu.costats.data.remote.response.mapper.toHistoryStats
import com.sahu.costats.data.remote.response.mapper.toProvinceStats
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val remote: RemoteService,
    private val local: LocalService,
) {

    fun getData(): Flow<List<CountryStats>> = local.getData()

    fun getProvinceData(country: String): Flow<List<ProvinceStats>> = local.getProvinceData(country)

    fun getHistoricData(country: String): Flow<List<HistoricStats>> = local.getHistoricData(country)

    suspend fun getDataFromApi(): Flow<Callback> = flow {
        val result = remote.getData().first()
        result.handle(
            onSuccess = {
                local.insertData(it.toCountryStats())
                emit(Callback.Success)
            },
            onFailure = {
                emit(Callback.Error(it.message))
            }
        )
    }

    suspend fun getProvinceDataFromApi(): Flow<Callback> = flow {
        val result = remote.getProvinceData().first()
        result.handle(
            onSuccess = {
                local.deleteAllProvinceData()
                local.insertProvinceData(it.toProvinceStats())
                emit(Callback.Success)
            },
            onFailure = {
                emit(Callback.Error(it.message))
            }
        )
    }

    suspend fun getHistoricDataFromApi(): Flow<Callback> = flow {
        val result = remote.getHistoricData().first()
        result.handle(
            onSuccess = {
                i("RESPONSE", it.toString())
                local.deleteAllHistoricData()
                local.insertHistoricData(it.toHistoryStats())
                emit(Callback.Success)
            },
            onFailure = {
                i("RESPONSE", "Failed ${it.printStackTrace()}")
                emit(Callback.Error(it.message))
            }
        )
    }

}