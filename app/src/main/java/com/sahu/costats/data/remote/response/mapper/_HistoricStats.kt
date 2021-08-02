package com.sahu.costats.data.remote.response.mapper

import com.sahu.costats.data.cache.dao.HistoricStats
import com.sahu.costats.data.remote.response.model.CountryHistoricalModel

fun List<CountryHistoricalModel>.toHistoryStats(): List<HistoricStats> = this.map { it.toHistoryStats() }

fun CountryHistoricalModel.toHistoryStats(): HistoricStats = HistoricStats(
    this.country,
    this.date,
    this.cases,
    this.deaths,
    this.recovered
)
