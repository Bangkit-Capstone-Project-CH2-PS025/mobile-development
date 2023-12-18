package com.itinergo.data.response.getitinerary


import com.google.gson.annotations.SerializedName

data class GetItineraryResponse(
    @SerializedName("data")
    val `data`: List<DataItinerary>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)