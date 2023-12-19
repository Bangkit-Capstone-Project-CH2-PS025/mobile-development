package com.itinergo.data.response.account


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("city")
    val city: List<City>,
    @SerializedName("count")
    val count: Int
)