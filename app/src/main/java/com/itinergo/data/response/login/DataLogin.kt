package com.itinergo.data.response.login


import com.google.gson.annotations.SerializedName

data class DataLogin(
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("role")
    val role: String,
    @SerializedName("username")
    val username: String
)