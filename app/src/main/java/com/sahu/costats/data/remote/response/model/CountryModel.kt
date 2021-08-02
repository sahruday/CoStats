package com.sahu.costats.data.remote.response.model

import com.google.gson.annotations.SerializedName

data class CountryModel(
    @SerializedName("continent")
    val continent: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("cases")
    val cases: Int,
    @SerializedName("deaths")
    val deaths: Int,
    @SerializedName("recovered")
    val recovered: Int,
    @SerializedName("active")
    val active: Int,
    @SerializedName("critical")
    val critical: Int,
    @SerializedName("todayCases")
    val todayCases: Int,
    @SerializedName("todayDeaths")
    val todayDeaths: Int,
    @SerializedName("todayRecovered")
    val todayRecovered: Int,
    @SerializedName("casesPerOneMillion")
    val casesPerMillion: Float,
    @SerializedName("deathsPerOneMillion")
    val deathsPerMillion: Float,
    @SerializedName("recoveredPerOneMillion")
    val recoveredPerMillion: Float,
    @SerializedName("activePerOneMillion")
    val activePerMillion: Float,
    @SerializedName("criticalPerOneMillion")
    val criticalPerMillion: Float,
    @SerializedName("population")
    val population: Int,
    @SerializedName("tests")
    val tests: Int,
    @SerializedName("testsPerOneMillion")
    val testsPerMillion: Int,
    @SerializedName("oneCasePerPeople")
    val oneCasePerPeople: Int,
    @SerializedName("oneDeathPerPeople")
    val oneDeathPerPeople: Int,
    @SerializedName("oneTestPerPeople")
    val oneTestPerPeople: Int,
    @SerializedName("countryInfo")
    val countryInfo: CountryInfo,
)