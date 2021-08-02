package com.sahu.costats.data.remote.response.model

data class CountryHistoricalModel(
    val country: String,
    val date: String,
    val cases: Int,
    val deaths: Int,
    val recovered: Int,
)