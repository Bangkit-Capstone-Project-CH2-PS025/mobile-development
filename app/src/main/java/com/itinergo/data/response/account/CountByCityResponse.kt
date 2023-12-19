package com.itinergo.data.response.account


import com.google.gson.annotations.SerializedName

data class CountByCityResponse(
    @SerializedName("data")
    val `data`: DataX,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)