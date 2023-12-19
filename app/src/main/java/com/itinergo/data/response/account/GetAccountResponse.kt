package com.itinergo.data.response.account


import com.google.gson.annotations.SerializedName

data class GetAccountResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)