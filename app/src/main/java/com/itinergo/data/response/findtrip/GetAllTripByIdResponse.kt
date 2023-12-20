package com.itinergo.data.response.findtrip


import com.google.gson.annotations.SerializedName

data class GetAllTripByIdResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)