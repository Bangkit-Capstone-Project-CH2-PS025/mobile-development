package com.itinergo.data.response.traveltips


import com.google.gson.annotations.SerializedName

data class GetDetailTravelTipsResponse(
    @SerializedName("data")
    val `data`: DataTravelTips,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)