package com.itinergo.data.response.account


import com.google.gson.annotations.SerializedName

data class UpdateProfileResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)