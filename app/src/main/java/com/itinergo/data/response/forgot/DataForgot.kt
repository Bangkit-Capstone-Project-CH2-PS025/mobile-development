package com.itinergo.data.response.forgot


import com.google.gson.annotations.SerializedName

data class DataForgot(
    @SerializedName("email")
    val email: String,
    @SerializedName("email_token")
    val emailToken: String
)