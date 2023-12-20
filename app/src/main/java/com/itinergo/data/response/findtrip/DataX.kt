package com.itinergo.data.response.findtrip


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("city")
    val city: String,
    @SerializedName("contact")
    val contact: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("departure")
    val departure: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("is_available")
    val isAvailable: Boolean,
    @SerializedName("persons")
    val persons: String,
    @SerializedName("until")
    val until: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: Int
)