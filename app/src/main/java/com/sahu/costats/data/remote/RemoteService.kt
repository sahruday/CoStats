package com.sahu.costats.data.remote

import android.util.Log.i
import com.sahu.appUtil.MyResult
import com.sahu.costats.data.remote.api.Api
import com.sahu.costats.data.remote.response.model.CountryHistoricalModel
import com.sahu.costats.data.remote.response.model.CountryModel
import com.sahu.costats.data.remote.response.model.ProvinceModel
import com.sahu.costats.data.remote.response.parser.HistoricDataParser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteService @Inject constructor(
    private val api: Api,
    private val parser: HistoricDataParser
) {

    suspend fun getData(): Flow<MyResult<List<CountryModel>>> = flow {
        val response = api.getData()
        if (response.isSuccessful && response.body().isNullOrEmpty().not())
            emit(MyResult.success(response.body()!!))
    }.catch { e ->
        emit(MyResult.failure(e))
    }

    suspend fun getProvinceData(): Flow<MyResult<List<ProvinceModel>>> = flow {
        val response = api.getProvinceData()
        if (response.isSuccessful && response.body().isNullOrEmpty().not())
            emit(MyResult.success(response.body()!!))
    }.catch { e ->
        emit(MyResult.failure(e))
    }

    suspend fun getHistoricData(): Flow<MyResult<List<CountryHistoricalModel>>> = flow {
        val response = api.getHistoricalData()
        i("RESPONSEbn", response.body().toString())
        if (response.isSuccessful && response.body().isNullOrEmpty().not())
            emit(MyResult.success(parser.parse(listOf(JSONObject(response.body()!!)))))
    }.catch { e ->
        emit(MyResult.failure(e))
    }

}