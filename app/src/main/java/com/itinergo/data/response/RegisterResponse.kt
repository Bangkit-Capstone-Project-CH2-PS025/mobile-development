package com.itinergo.data.response


import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("data")
    val `data`: DataRegister,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)