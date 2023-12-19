package com.itinergo.data.response.preferences


import com.google.gson.annotations.SerializedName

data class PostPreferencesResponse(
    @SerializedName("data")
    val `data`: List<String>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)