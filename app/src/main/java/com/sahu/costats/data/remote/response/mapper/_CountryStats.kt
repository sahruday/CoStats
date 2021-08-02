package com.sahu.costats.data.remote.response.mapper

import com.sahu.costats.data.cache.dao.CountryStats
import com.sahu.costats.data.remote.response.model.CountryModel

fun List<CountryModel>.toCountryStats(): List<CountryStats> = this.map { it.toCountryStats() }

fun CountryModel.toCountryStats(): CountryStats = CountryStats(
        this.continent,
        this.country,
        this.cases,
        this.deaths,
        this.recovered,
        this.active,
        this.critical,
        this.todayCases,
        this.todayDeaths,
        this.todayRecovered,
        this.casesPerMillion,
        this.deathsPerMillion,
        this.recoveredPerMillion,
        this.activePerMillion,
        this.criticalPerMillion,
        this.population,
        this.tests,
        this.testsPerMillion,
        this.oneCasePerPeople,
        this.oneDeathPerPeople,
        this.oneTestPerPeople,
        this.countryInfo.flag
    )