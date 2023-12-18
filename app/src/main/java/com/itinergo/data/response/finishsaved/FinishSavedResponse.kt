package com.itinergo.data.response.finishsaved


import com.google.gson.annotations.SerializedName

data class FinishSavedResponse(
    @SerializedName("data")
    val `data`: List<Int>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)