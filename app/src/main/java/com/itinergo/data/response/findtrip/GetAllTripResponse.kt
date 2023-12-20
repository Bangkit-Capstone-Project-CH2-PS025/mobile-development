package com.itinergo.data.response.findtrip


import com.google.gson.annotations.SerializedName

data class GetAllTripResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)