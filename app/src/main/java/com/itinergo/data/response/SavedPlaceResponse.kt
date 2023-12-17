package com.itinergo.data.response


import com.google.gson.annotations.SerializedName

data class SavedPlaceResponse(
    @SerializedName("data")
    val `data`: List<DataSavedPlace>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)