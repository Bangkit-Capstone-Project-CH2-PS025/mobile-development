package com.itinergo.data.response.account


import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("city")
    val city: String,
    @SerializedName("count")
    val count: Int
)