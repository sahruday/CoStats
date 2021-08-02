package com.sahu.costats.data.remote.response.model

import com.google.gson.annotations.SerializedName

data class CountryInfo(
    @SerializedName("flag")
    val flag: String
)