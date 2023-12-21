package com.itinergo.data.response.findtrip


import com.google.gson.annotations.SerializedName

data class DeleteFindTripResponse(
    @SerializedName("data")
    val `data`: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)