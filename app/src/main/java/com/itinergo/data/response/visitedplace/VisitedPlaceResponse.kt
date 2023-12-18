package com.itinergo.data.response.visitedplace


import com.google.gson.annotations.SerializedName

data class VisitedPlaceResponse(
    @SerializedName("data")
    val `data`: List<DataPlace>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)