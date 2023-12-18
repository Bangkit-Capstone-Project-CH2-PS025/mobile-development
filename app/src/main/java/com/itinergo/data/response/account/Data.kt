package com.itinergo.data.response.account


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("address")
    val address: Any,
    @SerializedName("email")
    val email: String,
    @SerializedName("email_token")
    val emailToken: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: Any,
    @SerializedName("is_verified")
    val isVerified: Boolean,
    @SerializedName("level_id")
    val levelId: Int,
    @SerializedName("level_traveler")
    val levelTraveler: LevelTraveler,
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