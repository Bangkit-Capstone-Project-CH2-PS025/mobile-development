package com.itinergo.data.response.preferences


import com.google.gson.annotations.SerializedName

data class CarbonResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)