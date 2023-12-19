package com.itinergo.data.response.traveltips


import com.google.gson.annotations.SerializedName

data class GetAllTravelTipsResponse(
    @SerializedName("data")
    val `data`: List<DataTravelTips>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)