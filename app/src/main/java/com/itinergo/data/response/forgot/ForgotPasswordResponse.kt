package com.itinergo.data.response.forgot


import com.google.gson.annotations.SerializedName

data class ForgotPasswordResponse(
    @SerializedName("data")
    val `data`: DataForgot,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)