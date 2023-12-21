package com.itinergo.data.response.discover


import com.google.gson.annotations.SerializedName

data class DiscoverPlaceResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)