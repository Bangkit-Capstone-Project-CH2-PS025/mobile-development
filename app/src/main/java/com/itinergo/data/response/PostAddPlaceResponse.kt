package com.itinergo.data.response


import com.google.gson.annotations.SerializedName

data class PostAddPlaceResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)