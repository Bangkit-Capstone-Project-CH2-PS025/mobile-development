package com.itinergo.data.response.savedplace


import com.google.gson.annotations.SerializedName

data class DetailSavedPlaceResponse(
    @SerializedName("data")
    val `data`: DataSavedPlace,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)