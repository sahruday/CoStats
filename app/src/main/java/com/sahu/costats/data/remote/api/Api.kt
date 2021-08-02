package com.sahu.costats.data.remote.api

import com.sahu.costats.data.remote.response.model.CountryModel
import com.sahu.costats.data.remote.response.model.ProvinceModel
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("v2/countries")
    suspend fun getData(
        @Query("yesterday") isYesterday: Int = 0
    ): Response<List<CountryModel>>


    @GET("v2/historical")
    suspend fun getHistoricalData(
        @Query("lastdays") days: Int = 10
    ): Response<String>

    @GET("v2/jhucsse")
    suspend fun getProvinceData() : Response<List<ProvinceModel>>
}