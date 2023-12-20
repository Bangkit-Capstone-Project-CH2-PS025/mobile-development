package com.itinergo.data.response.findtrip


import com.google.gson.annotations.SerializedName

data class CreateTripResponse(
    @SerializedName("data")
    val `data`: DataX,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)