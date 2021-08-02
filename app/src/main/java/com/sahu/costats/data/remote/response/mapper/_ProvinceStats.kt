package com.sahu.costats.data.remote.response.mapper

import com.sahu.costats.data.cache.dao.ProvinceStats
import com.sahu.costats.data.cache.dao.Stats
import com.sahu.costats.data.remote.response.model.ProvinceModel

fun List<ProvinceModel>.toProvinceStats() : List<ProvinceStats> = this.filter { it.province.isNullOrEmpty().not() }.map { it.toProvinceStats() }

fun ProvinceModel.toProvinceStats() : ProvinceStats =
    ProvinceStats(
        this.country,
        this.province!!,
        Stats(
            this.stats.confirmed,
            this.stats.deaths,
            this.stats.recovered
        )
    )