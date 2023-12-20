package com.itinergo.data.response.findtrip


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("address")
    val address: Any,
    @SerializedName("email")
    val email: String,
    @SerializedName("email_token")
    val emailToken: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: String,
    @SerializedName("is_verified")
    val isVerified: Boolean,
    @SerializedName("level_id")
    val levelId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: Any,
    @SerializedName("role")
    val role: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("xp")
    val xp: Int
)