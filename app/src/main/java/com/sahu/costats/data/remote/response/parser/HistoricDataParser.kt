package com.sahu.costats.data.remote.response.parser

import com.sahu.costats.data.remote.response.model.CountryHistoricalModel
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoricDataParser @Inject constructor() {
    fun parse(list: List<JSONObject>) : List<CountryHistoricalModel> =
        list.flatMap { it.toHistoricData() }


    private fun JSONObject.toHistoricData() : List<CountryHistoricalModel> {
        val country = this.getString("country")
        val timeLine = this.getJSONObject("timeline")
        val casesObj = timeLine.getJSONObject("cases")
        val recoveredObj = timeLine.getJSONObject("recovered")
        val deathsObj = timeLine.getJSONObject("deaths")

        val dates = casesObj.keys()
        val list = arrayListOf<CountryHistoricalModel>()
        dates.forEach {
            list.add(
                CountryHistoricalModel(
                    country,
                    it,
                    casesObj.getInt(it),
                    deathsObj.getInt(it),
                    recoveredObj.getInt(it)
                )
            )
        }
        return list
    }
}