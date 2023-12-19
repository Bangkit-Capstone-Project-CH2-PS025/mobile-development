package com.itinergo.data.response.account


import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("count")
    val count: Int,
    @SerializedName("country")
    val country: String
)