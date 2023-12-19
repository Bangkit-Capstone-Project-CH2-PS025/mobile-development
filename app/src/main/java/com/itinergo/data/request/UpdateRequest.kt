package com.itinergo.data.request

import com.google.gson.annotations.SerializedName

data class UpdateRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("images")
    val images: String
)