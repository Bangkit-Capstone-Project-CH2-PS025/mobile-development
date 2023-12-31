package com.itinergo.data.response.postitinerary


import com.google.gson.annotations.SerializedName

data class PostItineraryResponse(
    @SerializedName("data")
    val `data`: DataX,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)