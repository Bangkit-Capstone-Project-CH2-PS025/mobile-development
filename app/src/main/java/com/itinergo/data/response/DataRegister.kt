package com.itinergo.data.response


import com.google.gson.annotations.SerializedName

data class DataRegister(
    @SerializedName("email")
    val email: String,
    @SerializedName("is_verified")
    val isVerified: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("username")
    val username: String
)